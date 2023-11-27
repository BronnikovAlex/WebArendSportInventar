package com.arendinventar.controller;

import com.arendinventar.model.RoleUser;
import com.arendinventar.service.RoleUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleUserController {

    private final RoleUserService roleUserService;

    @Autowired
    public RoleUserController(RoleUserService roleUserService) {
        this.roleUserService = roleUserService;
    }

    @GetMapping
    public List<RoleUser> getAllRoles() {
        return roleUserService.getAllRoles();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleUser> getRoleById(@PathVariable Long id) {
        return roleUserService.getRoleById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public RoleUser createRole(@RequestBody RoleUser roleUser) {
        return roleUserService.saveRole(roleUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoleUser> updateRole(@PathVariable Long id, @RequestBody RoleUser updatedRole) {
        return roleUserService.getRoleById(id)
                .map(existingRole -> {
                    existingRole.setNameRoleUser(updatedRole.getNameRoleUser());
                    return ResponseEntity.ok(roleUserService.saveRole(existingRole));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable Long id) {
        roleUserService.deleteRole(id);
        return ResponseEntity.noContent().build();
    }

}
