package com.assignment.funix.assignment01.service.role;

import com.assignment.funix.assignment01.dao.RoleRepository;
import com.assignment.funix.assignment01.entity.Role;
import com.assignment.funix.assignment01.service.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Role findById(int id) {
        Optional<Role> result = roleRepository.findById(id);
        Role role = null;
        if(result.isPresent()){
            role = result.get();
        }else{
            throw new RuntimeException("Did not find the role id "+id);
        }
        return role;
    }

}
