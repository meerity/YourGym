package com.meerity.yourgym.service;

import com.meerity.yourgym.model.ClientCard;
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

    public ClientCard findByNumber(String number) {
        return clientCardRepository.findByCardNumber(number);
    }

    public LocalDate renewPayment(ClientCard clientCard) {
        LocalDate paymentDate = LocalDate.now();
        clientCard.setLastPaymentDate(paymentDate);
        clientCardRepository.save(clientCard);
        return paymentDate;
    }
}
