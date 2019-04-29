package com.sa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sa.model.ScoreGame;

@Repository
public interface ScoreGameRepository extends JpaRepository<ScoreGame, Long> {}
