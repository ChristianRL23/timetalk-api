package com.christian.time_connect;

import com.christian.time_connect.entities.PermissionEntity;
import com.christian.time_connect.entities.RoleEntity;
import com.christian.time_connect.entities.RoleEnum;
import com.christian.time_connect.entities.UserEntity;
import com.christian.time_connect.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Set;

@SpringBootApplication
public class TimeConnectApplication {

	public static void main(String[] args) {
		SpringApplication.run(TimeConnectApplication.class, args);
	}

	@Bean
	CommandLineRunner init(UserRepository userRepository) {
		return args -> {
			PermissionEntity createPermission = PermissionEntity.builder()
					.name("CREATE")
					.build();

			PermissionEntity readPermission = PermissionEntity.builder()
					.name("READ")
					.build();

			PermissionEntity updatePermission = PermissionEntity.builder()
					.name("UPDATE")
					.build();

			PermissionEntity deletePermission = PermissionEntity.builder()
					.name("DELETE")
					.build();

			RoleEntity roleUser = RoleEntity.builder()
					.roleEnum(RoleEnum.USER)
					.permissionList(Set.of(createPermission, readPermission, updatePermission, deletePermission))
					.build();

			UserEntity userChristian = UserEntity.builder()
					.firstName("Christian")
					.lastName("R. Lara")
					.email("christian@mail.com")
					.password("$2a$10$tbNIkKO4pr5FbyqIjOeRNODiJ/ffEU1v/tQJaXg7avqlr5tvMhzVq")
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(roleUser))
					.build();

			userRepository.save(userChristian);
		};
	}

}
