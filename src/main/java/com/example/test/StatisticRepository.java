package com.example.test;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Optional;

@Repository
public interface StatisticRepository extends JpaRepository<Statistic, Timestamp> {
    Optional<Statistic> findFirstByOrderByTimeSaveDesc();
}
