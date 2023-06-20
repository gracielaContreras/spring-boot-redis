package com.gcontreras.springbootredis;

import com.gcontreras.springbootredis.entities.Role;
import com.gcontreras.springbootredis.entities.User;
import com.gcontreras.springbootredis.entities.UserInRole;
import com.gcontreras.springbootredis.repositories.RoleRepository;
import com.gcontreras.springbootredis.repositories.UserInRoleRepository;
import com.gcontreras.springbootredis.repositories.UserRepository;
import com.gcontreras.springbootredis.utils.Roles;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Random;

@SpringBootApplication
public class SpringBootRedisApplication implements ApplicationRunner {
	@Autowired
	private Faker faker;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private UserInRoleRepository userInRoleRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRedisApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		Role role[] = { new Role(Roles.ADMIN.name()), new Role(Roles.USER.name()), new Role(Roles.SUPPORT.name()) };

		for(Role rol : role){
			roleRepository.save(rol);
		}

		for (int i = 0; i < 10; i++) {
			User user = new User();
			user.setPassword(faker.dragonBall().character());
			user.setUsername(faker.name().username());
			User userSave = userRepository.save(user);

			UserInRole userInRole = new UserInRole(userSave, role[new Random().nextInt(3)]);
			userInRoleRepository.save(userInRole);
		}
	}

}
