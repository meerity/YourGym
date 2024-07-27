package com.meerity.yourgym.service;

import com.meerity.yourgym.model.Trainer;
import com.meerity.yourgym.repositories.TrainerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class TrainerService {

    private final TrainerRepository trainerRepository;

    @Autowired
    public TrainerService(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }

    public boolean addTrainer(Trainer trainer) {
        return trainerRepository.save(trainer) != null;
    }

    public List<Trainer> getAllTrainers() {
        return trainerRepository.findAll();
    }

    public List<Trainer> getAllFreeTrainers() {
        return trainerRepository.getAllFreeTrainers();
    }
}
