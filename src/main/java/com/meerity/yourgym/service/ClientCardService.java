package com.meerity.yourgym.service;

import com.meerity.yourgym.model.entity.ClientCard;
import com.meerity.yourgym.repositories.ClientCardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Slf4j
@Service
public class ClientCardService {

    private final ClientCardRepository clientCardRepository;


    @Autowired
    public ClientCardService(ClientCardRepository clientCardRepository) {
        this.clientCardRepository = clientCardRepository;
    }

    public long getAllClientsCount() {
        return clientCardRepository.count();
    }

    public LocalDate renewPaymentAndGetNewDate(ClientCard clientCard) {
        LocalDate paymentDate = LocalDate.now();
        clientCard.setLastPaymentDate(paymentDate);
        clientCardRepository.save(clientCard);
        return paymentDate;
    }

    public boolean updatePaymentDate(ClientCard clientCard) {
        try {
            clientCard.setLastPaymentDate(LocalDate.now());
            clientCardRepository.save(clientCard);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    public boolean deleteClientCardByNumber(String cardNumber) {
        ClientCard clientCard = clientCardRepository.findByCardNumber(cardNumber);
        if (clientCard != null) {
            clientCardRepository.delete(clientCard);
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteClientCard(ClientCard clientCard) {
        try {
            clientCardRepository.delete(clientCard);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }
}
