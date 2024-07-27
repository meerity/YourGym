package com.meerity.yourgym.service;

import com.meerity.yourgym.model.Person;
import com.meerity.yourgym.model.RegistrationForm;
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


    @Autowired
    public PersonService(PersonRepository personRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
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
}
