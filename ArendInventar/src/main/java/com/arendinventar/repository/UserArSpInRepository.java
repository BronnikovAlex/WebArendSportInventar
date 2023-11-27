package com.arendinventar.repository;


import com.arendinventar.model.UserArSpIn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserArSpInRepository extends JpaRepository<UserArSpIn, Long> {
    Optional<UserArSpIn> findByLogin(String login);
}
