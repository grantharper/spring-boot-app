package com.bootstrap;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.domain.FormField;
import com.domain.Product;
import com.domain.Role;
import com.domain.User;
import com.repository.FormFieldRepository;
import com.repository.ProductRepository;
import com.service.RoleService;
import com.service.UserService;

@Component
public class SpringJpaBootstrap implements ApplicationListener<ContextRefreshedEvent> {

	@Resource
	private ProductRepository productRepository;
	@Resource
	private FormFieldRepository formFieldRepository;
	@Resource
	private UserService userService;
	@Resource
	private RoleService roleService;

	private Logger log = Logger.getLogger(SpringJpaBootstrap.class);

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {

		loadProducts();
		loadUsers();
		loadRoles();
		assignUsersToUserRole();
		assignUsersToAdminRole();
		loadFormFields();

	}

	private void assignUsersToAdminRole() {
		List<Role> roles = (List<Role>) roleService.listAll();
        List<User> users = (List<User>) userService.listAll();
 
        roles.forEach(role -> {
            if (role.getRole().equalsIgnoreCase("ADMIN")) {
                users.forEach(user -> {
                    if (user.getUsername().equals("admin")) {
                        user.addRole(role);
                        userService.saveOrUpdate(user);
                    }
                });
            }
        });

	}

	private void assignUsersToUserRole() {
		List<Role> roles = (List<Role>) roleService.listAll();
        List<User> users = (List<User>) userService.listAll();
 
        roles.forEach(role -> {
            if (role.getRole().equalsIgnoreCase("USER")) {
                users.forEach(user -> {
                    if (user.getUsername().equals("user")) {
                        user.addRole(role);
                        userService.saveOrUpdate(user);
                    }
                });
            }
        });

	}

	private void loadRoles() {
		 Role role = new Role();
	        role.setRole("USER");
	        roleService.saveOrUpdate(role);
	        log.info("Saved role" + role.getRole());
	        Role adminRole = new Role();
	        adminRole.setRole("ADMIN");
	        roleService.saveOrUpdate(adminRole);
	        log.info("Saved role" + adminRole.getRole());

	}

	private void loadUsers() {
		User user1 = new User();
		user1.setUsername("user");
		user1.setPassword("user");
		userService.saveOrUpdate(user1);

		User user2 = new User();
		user2.setUsername("admin");
		user2.setPassword("admin");
		userService.saveOrUpdate(user2);

	}

	private void loadProducts() {
		Product shirt = new Product();
		shirt.setDescription("Spring Framework Guru Shirt");
		shirt.setPrice(new BigDecimal("18.95"));
		shirt.setImageUrl(
				"https://springframework.guru/wp-content/uploads/2015/04/spring_framework_guru_shirt-rf412049699c14ba5b68bb1c09182bfa2_8nax2_512.jpg");
		shirt.setProductId("235268845711068308");
		productRepository.save(shirt);

		log.info("Saved Shirt - id: " + shirt.getId());

		Product mug = new Product();
		mug.setDescription("Spring Framework Guru Mug");
		mug.setPrice(new BigDecimal("13.95"));
		mug.setImageUrl(
				"https://springframework.guru/wp-content/uploads/2015/04/spring_framework_guru_coffee_mug-r11e7694903c348e1a667dfd2f1474d95_x7j54_8byvr_512.jpg");
		mug.setProductId("168639393495335947");
		productRepository.save(mug);

		log.info("Saved Mug - id:" + mug.getId());

	}
	
	private void loadFormFields(){
		
		FormField field1 = new FormField();
		field1.setLabel("First Name");
		field1.setName("firstName");
		field1.setValue("Grant");
		field1.setRequired(true);
		field1.setValidation("^[A-Z]*$");
		
		FormField field2 = new FormField();
		field2.setLabel("Last Name");
		field2.setName("lastName");
		field2.setValue("Harper");
		field1.setRequired(true);
		field2.setValidation("^[a-z]*$");
		
		formFieldRepository.save(field1);
		formFieldRepository.save(field2);
		
	}

}
