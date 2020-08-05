package my.demo.bookmark.listener;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import my.demo.bookmark.entity.Privilege;
import my.demo.bookmark.entity.Role;
import my.demo.bookmark.entity.User;
import my.demo.bookmark.repository.PrivilegeRepository;
import my.demo.bookmark.repository.RoleRepository;
import my.demo.bookmark.repository.UserRepository;

@Component
public class SecurityInitialDataLoader implements
  ApplicationListener<ContextRefreshedEvent> {
 
    boolean initDataLoaded = false;
 
    @Autowired
    private UserRepository userRepository;
 
    @Autowired
    private RoleRepository roleRepository;
 
    @Autowired
    private PrivilegeRepository privilegeRepository;
 
    @Autowired
    private PasswordEncoder passwordEncoder;
 
    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
    	initDataLoaded = initDataLoaded || userRepository.count() > 0;
    	
        if (initDataLoaded)
            return;
        Privilege readPrivilege
          = createPrivilegeIfNotFound("READ_PRIVILEGE");
        Privilege writePrivilege
          = createPrivilegeIfNotFound("WRITE_PRIVILEGE");
 
        List<Privilege> adminPrivileges = Arrays.asList(
          readPrivilege, writePrivilege);        
        createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
        createRoleIfNotFound("ROLE_USER", Arrays.asList(readPrivilege));
 
        Role userRole = roleRepository.findByName("ROLE_USER");
        Role adminRole = roleRepository.findByName("ROLE_ADMIN");
       
        User testUser = new User();
        testUser.setFirstName("Test");
        testUser.setLastName("Test");
        testUser.setPassword(passwordEncoder.encode("test"));
        testUser.setUsername("test");
        testUser.setEmail("testuser@example.com");
        testUser.setRoles(Arrays.asList(userRole));
        testUser.setEnabled(true);
        userRepository.save(testUser);
        
        User user = new User();
        user.setFirstName("Admin");
        user.setLastName("Admin");
        user.setPassword(passwordEncoder.encode("admin"));
        user.setUsername("admin");
        user.setEmail("testadmin@example.com");
        user.setRoles(Arrays.asList(userRole, adminRole));
        user.setEnabled(true);
        userRepository.save(user);
 
        initDataLoaded = true;
    }
 
    @Transactional
    Privilege createPrivilegeIfNotFound(String name) {
 
        Privilege privilege = privilegeRepository.findByName(name);
        if (privilege == null) {
            privilege = new Privilege(name);
            privilegeRepository.save(privilege);
        }
        return privilege;
    }
 
    @Transactional
    Role createRoleIfNotFound(
      String name, Collection<Privilege> privileges) {
 
        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role(name);
            role.setPrivileges(privileges);
            roleRepository.save(role);
        }
        return role;
    }
}