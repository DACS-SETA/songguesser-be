package com.songguesser.backend.model.repository;

import com.songguesser.backend.model.entity.Game;
import com.songguesser.backend.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    long countByUser(User user);
}
