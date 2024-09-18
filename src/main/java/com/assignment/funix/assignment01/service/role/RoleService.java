package com.assignment.funix.assignment01.service.role;

import com.assignment.funix.assignment01.entity.Role;

import java.util.List;

public interface RoleService {
    List<Role> findAll();
    Role saveRole(Role role);
    Role findById(int id);
}
