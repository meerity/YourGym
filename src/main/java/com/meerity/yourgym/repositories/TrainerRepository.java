package com.meerity.yourgym.repositories;

import com.meerity.yourgym.model.dto.ClientInfoDTO;
import com.meerity.yourgym.model.dto.TrainerAndTraineesDTO;
import com.meerity.yourgym.model.entity.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, Integer> {

    Trainer findByTrainerId(long id);

    Trainer findByTrainerFirstNameAndTrainerLastName(String firstName, String lastName);

    @Query("SELECT new com.meerity.yourgym.model.dto.TrainerAndTraineesDTO(t.trainerFirstName, t.trainerLastName, COUNT(cc.cardId)) " +
            "FROM Trainer t " +
            "LEFT JOIN t.clientCards cc " +
            "GROUP BY t.trainerId, t.trainerFirstName, t.trainerLastName " +
            "ORDER BY t.trainerId")
    List<TrainerAndTraineesDTO> getAllTrainersAndTraineesCount();

    @Query("select t from Trainer t " +
            "left join ClientCard cc on t.trainerId = cc.trainer.trainerId" +
            " GROUP BY t.trainerId having COUNT(cc.cardId) <= 30" +
            " order by t.trainerId")
    List<Trainer> getAllFreeTrainers();

    @Query("SELECT new com.meerity.yourgym.model.dto.ClientInfoDTO(cc.cardNumber, p.firstName, p.lastName, p.phoneNum, cc.lastPaymentDate) " +
            " FROM ClientCard cc " +
            "LEFT JOIN Person p ON cc.cardId = p.card.cardId " +
            "WHERE cc.trainer.trainerId = :trainerIdForSearch " +
            "ORDER BY p.lastName, p.firstName")
    List<ClientInfoDTO> getAllPeopleInfoByTrainerId(Long trainerIdForSearch);
}
