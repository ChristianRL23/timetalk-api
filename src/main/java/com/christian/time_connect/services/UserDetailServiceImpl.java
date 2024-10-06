package com.christian.time_connect.services;

import com.christian.time_connect.dto.AuthCreateUserRequest;
import com.christian.time_connect.dto.AuthLoginRequest;
import com.christian.time_connect.dto.AuthResponse;
import com.christian.time_connect.entities.RoleEntity;
import com.christian.time_connect.entities.UserEntity;
import com.christian.time_connect.exceptions.EmailAlreadyExistsException;
import com.christian.time_connect.repositories.RoleRepository;
import com.christian.time_connect.repositories.UserRepository;
import com.christian.time_connect.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findUserEntityByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("The user doesn't exist."));

        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();

        userEntity.getRoles()
                .forEach(role -> authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(role.getRoleEnum().name()))));

        userEntity.getRoles().stream()
                .flatMap(role -> role.getPermissionList().stream())
                .forEach(permission -> authorityList.add(new SimpleGrantedAuthority(permission.getName())));

        return new User(
                userEntity.getEmail(),
                userEntity.getPassword(),
                userEntity.isEnabled(),
                userEntity.isAccountNoExpired(),
                userEntity.isCredentialNoExpired(),
                userEntity.isAccountNoLocked(),
                authorityList
        );
    }

    public AuthResponse createUser(AuthCreateUserRequest authCreateUserRequest) {
        String firstName = authCreateUserRequest.firstName();
        String lastName = authCreateUserRequest.lastName();
        String email = authCreateUserRequest.email();
        String password = authCreateUserRequest.password();
        List<String> roleRequest = authCreateUserRequest.roleRequest().roleListName();

        if (userRepository.existsByEmail(email)) {
            throw new EmailAlreadyExistsException("The email is already in use");
        }

        Set<RoleEntity> roleEntitySet = roleRepository.findRoleEntitiesByRoleEnumIn(roleRequest).stream().collect(Collectors.toSet());

        if (roleEntitySet.isEmpty()) {
            throw new IllegalArgumentException("The roles specified does not exist.");
        }

        UserEntity userEntity = UserEntity.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .password(passwordEncoder.encode(password))
                .roles(roleEntitySet)
                .isEnabled(true)
                .accountNoLocked(true)
                .accountNoExpired(true)
                .credentialNoExpired(true)
                .build();

        UserEntity userCreated = userRepository.save(userEntity);

        ArrayList<SimpleGrantedAuthority> authorityList = new ArrayList<>();

        userCreated.getRoles().forEach(role -> authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(role.getRoleEnum().name()))));

        userCreated.getRoles()
                .stream()
                .flatMap(role -> role.getPermissionList().stream())
                .forEach(permission -> authorityList.add(new SimpleGrantedAuthority(permission.getName())));

        Authentication authentication = new UsernamePasswordAuthenticationToken(userCreated.getEmail(), userCreated.getPassword(), authorityList);

        String accessToken = jwtUtils.createToken(authentication);

        return new AuthResponse(userCreated.getEmail(), "User created successfully", accessToken, true);
    }

    public AuthResponse loginUser(AuthLoginRequest authLoginRequest) {
        String userEmail = authLoginRequest.email();
        String userPassword = authLoginRequest.password();
        UserDetails userDetails = null;

        if (userRepository.existsByEmail(userEmail)) {
            userDetails = loadUserByUsername(userEmail);
            if (!passwordEncoder.matches(userPassword, userDetails.getPassword())) {
                throw new BadCredentialsException("The email or password is incorrect.");
            }
        } else {
            throw new BadCredentialsException("The email or password is incorrect.");
        }

        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(
                userEmail,
                userDetails.getPassword(),
                userDetails.getAuthorities()
        );

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        String accessToken = jwtUtils.createToken(authenticationToken);

        return new AuthResponse(userEmail, "User logged successfully.", accessToken, true);
    }
}
