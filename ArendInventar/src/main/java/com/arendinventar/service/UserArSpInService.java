package com.arendinventar.service;

import com.arendinventar.model.PostUser;
import com.arendinventar.model.RoleUser;
import com.arendinventar.model.UserArSpIn;
import com.arendinventar.repository.PostUserRepository;
import com.arendinventar.repository.RoleUserRepository;
import com.arendinventar.repository.UserArSpInRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserArSpInService {

    private final UserArSpInRepository userArSpInRepository;
    private final RoleUserRepository roleUserRepository;
    private final PostUserRepository postUserRepository;


    @Autowired
    public UserArSpInService(UserArSpInRepository userArSpInRepository, RoleUserRepository roleUserRepository,  PostUserRepository postUserRepository) {
        this.userArSpInRepository = userArSpInRepository;
        this.roleUserRepository = roleUserRepository;
        this.postUserRepository = postUserRepository;

    }

    public List<UserArSpIn> getAllUsers() {
        return userArSpInRepository.findAll();
    }

    public Optional<UserArSpIn> getUserById(Long id) {
        return userArSpInRepository.findById(id);
    }

    public UserArSpIn saveUser(UserArSpIn userArSpIn) {
        // Проверка наличия пользователя с таким логином
        Optional<UserArSpIn> existingUser = userArSpInRepository.findByLogin(userArSpIn.getLogin());

        if (existingUser.isPresent()) {
            // Пользователь с таким логином уже существует
            return null;
        }

        if (userArSpIn.getRoleUser() != null && userArSpIn.getRoleUser().getIdRoleUser() == null) {
            userArSpIn.setRoleUser(roleUserRepository.save(userArSpIn.getRoleUser()));
        }

        if (userArSpIn.getPostUser() != null && userArSpIn.getPostUser().getIdPostUser() == null) {
            userArSpIn.setPostUser(postUserRepository.save(userArSpIn.getPostUser()));
        }

        // Создаем объекты RoleUser и PostUser
        RoleUser defaultRole = roleUserRepository.findById(3L).orElse(null); // Подставьте фактический идентификатор роли
        PostUser defaultPost = postUserRepository.findById(3L).orElse(null); // Подставьте фактический идентификатор должности

        // Устанавливаем созданные объекты в соответствующие поля
        userArSpIn.setRoleUser(defaultRole);
        userArSpIn.setPostUser(defaultPost);

        return userArSpInRepository.save(userArSpIn);
    }

    public void deleteUser(Long id) {
        userArSpInRepository.deleteById(id);
    }

    public Optional<UserArSpIn> findByLogin(String login) {
        return userArSpInRepository.findByLogin(login);
    }

}
