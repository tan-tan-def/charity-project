package com.assignment.funix.assignment01;

import com.assignment.funix.assignment01.entity.Role;
import com.assignment.funix.assignment01.service.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataSeeder implements CommandLineRunner {
    private RoleService roleService;
    @Autowired
    public DataSeeder(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public void run(String... args) throws Exception {
        Role adminRole = new Role("ADMIN");
        Role userRole = new Role("USER");
        List<Role> roles = roleService.findAll();
        if(roles.isEmpty()){
            roleService.saveRole(adminRole);
            roleService.saveRole(userRole);
        }
    }
}
