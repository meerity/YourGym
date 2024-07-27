package com.meerity.yourgym.service;

import com.meerity.yourgym.model.ClientCard;
import com.meerity.yourgym.model.NewClient;
import com.meerity.yourgym.model.Person;
import com.meerity.yourgym.repositories.ClientCardRepository;
import com.meerity.yourgym.repositories.PersonRepository;
import com.meerity.yourgym.repositories.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Slf4j
@Service
public class AddClientService {

    private final PersonRepository personRepository;
    private final ClientCardRepository clientCardRepository;
    private final RoleRepository roleRepository;

    public AddClientService(PersonRepository personRepository, ClientCardRepository clientCardRepository, RoleRepository roleRepository) {
        this.personRepository = personRepository;
        this.clientCardRepository = clientCardRepository;
        this.roleRepository = roleRepository;
    }

    public boolean addNewClient(NewClient newClient) {
        ClientCard newCard = new ClientCard();
        newCard.setCardNumber(newClient.getCardNumber());
        newCard.setLastPaymentDate(LocalDate.now());

        Person newPerson = new Person();
        newPerson.setFirstName(newClient.getFirstName());
        newPerson.setLastName(newClient.getLastName());
        newPerson.setPhoneNum(newClient.getPhoneNum());
        newPerson.setCard(newCard);
        newPerson.setRole(roleRepository.findByName("CLIENT_UNREGISTERED"));

        clientCardRepository.save(newCard);
        Person checkPerson = personRepository.save(newPerson);

        return checkPerson != null && checkPerson.getCard() != null;
    }
}
