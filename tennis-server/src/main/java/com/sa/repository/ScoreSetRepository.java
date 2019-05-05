package com.sa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.sa.model.ScoreSet;

@Repository
public interface ScoreSetRepository extends JpaRepository<ScoreSet, Long> , JpaSpecificationExecutor<ScoreSet>{}
