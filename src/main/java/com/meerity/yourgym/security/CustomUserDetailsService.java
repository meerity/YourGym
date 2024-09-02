package com.meerity.yourgym.security;

import com.meerity.yourgym.model.entity.Person;
import com.meerity.yourgym.repositories.PersonRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService implements UserDetailsService {


    private final PersonRepository personRepository;

    public CustomUserDetailsService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = personRepository.findByEmail(username);
        if (person == null) {
            person = personRepository.findByPhoneNum(username);
        }
        if (person == null) {
            throw new UsernameNotFoundException("User not found with identifier " + username);
        }
        return User.withUsername(person.getEmail())
                .password(person.getPsw())
                .roles(person.getRole().toString())
                .build();
    }
}
