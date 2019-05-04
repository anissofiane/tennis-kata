package com.sa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.sa.model.SetTennis;

@Repository
public interface SetTennisRepository extends JpaRepository<SetTennis, Long> , JpaSpecificationExecutor<SetTennis>{}
