package com.example.angularjwtbe.service.role;


import com.example.angularjwtbe.model.Role;
import com.example.angularjwtbe.model.RoleName;

import java.util.Optional;

public interface IRoleService {
    Optional<Role> findByName(RoleName name);
}