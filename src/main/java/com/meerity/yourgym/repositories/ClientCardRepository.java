package com.meerity.yourgym.repositories;

import com.meerity.yourgym.model.entity.ClientCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientCardRepository extends JpaRepository<ClientCard, Long> {

    ClientCard findByCardNumber(String cardNumber);
}
