package com.meerity.yourgym.service;

import com.meerity.yourgym.model.*;
import com.meerity.yourgym.repositories.PersonRepository;
import com.meerity.yourgym.repositories.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PersonService {


    private final PersonRepository personRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final TrainerService trainerService;


    @Autowired
    public PersonService(PersonRepository personRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, TrainerService trainerService) {
        this.personRepository = personRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.trainerService = trainerService;
    }

    public boolean registerPerson(RegistrationForm registrationForm) {
        String cardNumber = registrationForm.getCardNumber();
        Person person = personRepository.findByCardCardNumber(cardNumber);
        if (person != null && person.getRole().getName().equals("CLIENT_UNREGISTERED")) {
            person.setEmail(registrationForm.getEmail());
            person.setPsw(passwordEncoder.encode(registrationForm.getPassword()));
            person.setRole(roleRepository.findByName("CLIENT"));
            personRepository.save(person);
            return true;
        }
        return false;
    }

    public Person findByEmailOrPhoneNumber(String emailOrPhone) {
        if (emailOrPhone.matches("^\\d{10}")) {
            return personRepository.findByPhoneNum(emailOrPhone);
        }
        else {
            return personRepository.findByEmail(emailOrPhone);
        }
    }

    public Person findByCardNumber(String cardNumber) {
        return personRepository.findByCardCardNumber(cardNumber);
    }

    public Person findByEmail(String email) {
        return personRepository.findByEmail(email);
    }

    public Person findByPhoneNum(String phoneNum) {
        return personRepository.findByPhoneNum(phoneNum);
    }

    public Person updatePerson(Person person, EditForm editForm) {
        person.setFirstName(editForm.getFormFirstName());
        person.setLastName(editForm.getFormLastName());
        person.setPhoneNum(editForm.getFormPhoneNum());
        person.setEmail(editForm.getFormEmail());
        personRepository.save(person);
        return person;
    }

    public Person updatePersonOP(EditFormWithTrainer editFormT, Person person) {
        person.setFirstName(editFormT.getFormFirstName());
        person.setLastName(editFormT.getFormLastName());
        person.setPhoneNum(editFormT.getFormPhoneNum());
        if (editFormT.getFormEmail() != null) {
            person.setEmail(editFormT.getFormEmail());
        }
        Trainer newTrainer = trainerService.getTrainerById(editFormT.getTrainerId());
        person.getCard().setTrainer(newTrainer);
        personRepository.save(person);
        return person;
    }
}
