package com.arendinventar.controller;

import com.arendinventar.model.UserArSpIn;
import com.arendinventar.service.UserArSpInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class UserArSpInController {
    private final UserArSpInService userArSpInService;

    @Autowired
    public UserArSpInController(UserArSpInService userArSpInService) {
        this.userArSpInService = userArSpInService;
    }

    @GetMapping
    public List<UserArSpIn> getAllUsers() {
        return userArSpInService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserArSpIn> getUserById(@PathVariable Long id) {
        return userArSpInService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public UserArSpIn createUser(@RequestBody UserArSpIn userArSpIn) {
        return userArSpInService.saveUser(userArSpIn);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserArSpIn> updateUser(@PathVariable Long id, @RequestBody UserArSpIn updatedUser) {
        return userArSpInService.getUserById(id)
                .map(existingUser -> {
                    // Update user properties
                    existingUser.setFamily(updatedUser.getFamily());
                    existingUser.setNameU(updatedUser.getNameU());
                    existingUser.setPatronymic(updatedUser.getPatronymic());
                    existingUser.setLogin(updatedUser.getLogin());
                    existingUser.setPassword(updatedUser.getPassword());
                    existingUser.setContactInformation(updatedUser.getContactInformation());
                    existingUser.setDateBrit(updatedUser.getDateBrit());
                    existingUser.setRoleUser(updatedUser.getRoleUser());
                    existingUser.setPostUser(updatedUser.getPostUser());
                    existingUser.setNumberTeleph(updatedUser.getNumberTeleph());

                    return ResponseEntity.ok(userArSpInService.saveUser(existingUser));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userArSpInService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{login}")
    public ResponseEntity<UserArSpIn> getUserByLogin(@PathVariable String login) {
        return userArSpInService.findByLogin(login)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(
            @RequestParam String login,
            @RequestParam String password
    ) {
        Optional<UserArSpIn> user = userArSpInService.findByLogin(login);

        if (user.isPresent() && user.get().getPassword().equals(password)) {
            Map<String, Object> userData = Map.of(
                    "message", "Вход успешен",
                    "user", user.get()
            );
            return ResponseEntity.ok(userData);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Ошибка входа: неверный логин или пароль"));
        }
    }
}
