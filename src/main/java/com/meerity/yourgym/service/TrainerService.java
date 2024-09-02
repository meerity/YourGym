package com.meerity.yourgym.service;

import com.meerity.yourgym.model.dto.ClientInfoDTO;
import com.meerity.yourgym.model.dto.TrainerAndTraineesDTO;
import com.meerity.yourgym.model.entity.Trainer;
import com.meerity.yourgym.repositories.TrainerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

    public List<TrainerAndTraineesDTO> getAllTrainersAndTraineeCount(){
        return trainerRepository.getAllTrainersAndTraineesCount();
    }

    public Map<Trainer, List<ClientInfoDTO>> getClientInfoByTrainer() {
        List<Trainer> trainers = trainerRepository.findAll();
        Map<Trainer, List<ClientInfoDTO>> clientInfoByTrainer = new LinkedHashMap<>();
        for (Trainer trainer : trainers) {
            List<ClientInfoDTO> clientInfo = trainerRepository.getAllPeopleInfoByTrainerId(trainer.getTrainerId());
            clientInfoByTrainer.put(trainer, clientInfo);
        }
        return clientInfoByTrainer;
    }

    public Trainer getTrainerById(long id) {
        return trainerRepository.findByTrainerId(id);
    }

    public boolean deleteTrainerByFullName(String firstName, String lastName) {
        Trainer trainer = trainerRepository.findByTrainerFirstNameAndTrainerLastName(firstName, lastName);
        if (trainer != null) {
            trainerRepository.delete(trainer);
            return true;
        } else {
            return false;
        }
    }
}
