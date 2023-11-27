package com.arendinventar.service;

import com.arendinventar.model.RoleUser;
import com.arendinventar.repository.RoleUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleUserService {

    private final RoleUserRepository roleUserRepository;

    @Autowired
    public RoleUserService(RoleUserRepository roleUserRepository) {
        this.roleUserRepository = roleUserRepository;
    }

    public List<RoleUser> getAllRoles() {
        return roleUserRepository.findAll();
    }

    public Optional<RoleUser> getRoleById(Long id) {
        return roleUserRepository.findById(id);
    }

    public RoleUser saveRole(RoleUser roleUser) {
        return roleUserRepository.save(roleUser);
    }

    public void deleteRole(Long id) {
        roleUserRepository.deleteById(id);
    }


}
