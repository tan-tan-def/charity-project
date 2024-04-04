package com.assignment.funix.assignment01.service.role;

import com.assignment.funix.assignment01.dao.RoleRepository;
import com.assignment.funix.assignment01.entity.Role;
import com.assignment.funix.assignment01.service.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoleServiceImpl implements RoleService {
    private RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

}
