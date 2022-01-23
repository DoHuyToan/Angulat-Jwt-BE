package com.example.angularjwtbe.service.role;

import com.example.angularjwtbe.model.Role;
import com.example.angularjwtbe.model.RoleName;
import com.example.angularjwtbe.repository.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService implements IRoleService {
    @Autowired
    IRoleRepository roleRepository;
    @Override
    public Optional<Role> findByName(RoleName name) {
        return roleRepository.findByName(name);
    }
}
