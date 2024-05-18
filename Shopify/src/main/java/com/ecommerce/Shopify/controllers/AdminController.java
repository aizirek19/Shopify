package com.ecommerce.Shopify.controllers;

import com.ecommerce.Shopify.entities.Role;
import com.ecommerce.Shopify.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private RoleService userRolesService;
    // role controllers

    @PostMapping("/role")
    public ResponseEntity<Role> saveRole(@RequestBody Role userRole) {
        Role newRoles = userRolesService.saveRole(userRole);
        return ResponseEntity.ok(newRoles);
    }

    @PostMapping("/roles")
    public ResponseEntity<List<Role>> saveRoles(@RequestBody Set<Role> userRoles) {
        List<Role> newRoles = userRolesService.saveRoles(userRoles);
        return ResponseEntity.ok(newRoles);
    }

    @GetMapping("/role/{roleName}")
    public ResponseEntity<Role> getRole(@PathVariable String roleName) {
        Role newRoles = userRolesService.getRole(roleName);
        return ResponseEntity.ok(newRoles);
    }

    @GetMapping("/roles")
    public ResponseEntity<List<Role>> getRoles() {
        List<Role> newRoles = userRolesService.getRoles();
        return ResponseEntity.ok(newRoles);
    }
}