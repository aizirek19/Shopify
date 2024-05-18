package com.ecommerce.Shopify.service;

import com.ecommerce.Shopify.entities.Role;
import com.ecommerce.Shopify.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
public class RoleService {

    @Autowired
    private RoleRepository userRolesRepository;

    public List<Role> saveRoles(Set<Role> roles){
        return userRolesRepository.saveAll(roles);
    }

    public Role saveRole(Role role){
        return userRolesRepository.save(role);
    }

    public List<Role> getRoles(){
        return userRolesRepository.findAll();
    }

    public Role getRole(String roleName){
        Optional<Role> result = userRolesRepository.findByRoleName(roleName);
        if(result.isPresent()) {
            return result.get();
        }else {
            return new Role();
        }
    }
}
