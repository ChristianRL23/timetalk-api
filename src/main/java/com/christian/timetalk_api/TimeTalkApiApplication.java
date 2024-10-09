package com.christian.timetalk_api;

import com.christian.timetalk_api.entities.PermissionEntity;
import com.christian.timetalk_api.entities.RoleEntity;
import com.christian.timetalk_api.entities.RoleEnum;
import com.christian.timetalk_api.entities.UserEntity;
import com.christian.timetalk_api.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.List;
import java.util.Set;

@SpringBootApplication
@EnableJpaAuditing
public class TimeTalkApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TimeTalkApiApplication.class, args);
	}

	@Bean
	CommandLineRunner init(UserRepository userRepository) {
		List<UserEntity> existingData = userRepository.findAll();
			return args -> {
				if (existingData.isEmpty()) {
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

					UserEntity initialUser = UserEntity.builder()
							.firstName("John")
							.lastName("Doe")
							.email("john@mail.com")
							.password("$2a$10$tbNIkKO4pr5FbyqIjOeRNODiJ/ffEU1v/tQJaXg7avqlr5tvMhzVq")
							.isEnabled(true)
							.accountNoExpired(true)
							.accountNoLocked(true)
							.credentialNoExpired(true)
							.roles(Set.of(roleUser))
							.build();

					userRepository.save(initialUser);
				}
			};
    }
}
