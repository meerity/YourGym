package com.meerity.yourgym.repositories;

import com.meerity.yourgym.model.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, Integer> {

    Trainer findByTrainerId(long id);


    @Query("select t from Trainer t " +
            "left join ClientCard cc on t.trainerId = cc.trainer.trainerId" +
            " GROUP BY t.trainerId having COUNT(cc.cardId) <= 30" +
            " order by t.trainerId")
    List<Trainer> getAllFreeTrainers();
}
