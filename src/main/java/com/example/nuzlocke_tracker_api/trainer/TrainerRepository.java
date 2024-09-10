package com.example.nuzlocke_tracker_api.trainer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, Integer> {
    Optional<Trainer> findById(Integer id);
    List<Trainer> findByUserId(Integer userId);
}

