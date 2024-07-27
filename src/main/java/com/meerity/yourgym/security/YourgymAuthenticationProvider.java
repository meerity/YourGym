package com.meerity.yourgym.security;

import com.meerity.yourgym.model.Person;
import com.meerity.yourgym.model.Role;
import com.meerity.yourgym.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class YourgymAuthenticationProvider implements AuthenticationProvider {

    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public YourgymAuthenticationProvider(PersonRepository personRepository, PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.personRepository = personRepository;
    }


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String emailOrPhone = authentication.getName();
        String password = authentication.getCredentials().toString();
        Person person;
        if (emailOrPhone.matches("\\d{12}")) {
            person = personRepository.findByPhoneNum(emailOrPhone);
        } else {
            person = personRepository.findByEmail(emailOrPhone);
        }
//        && passwordEncoder.matches(password, person.getPsw())
        if (person != null ) {
            return new UsernamePasswordAuthenticationToken(emailOrPhone, password, getAuthorities(person.getRole()));
        }
        else {
            throw new BadCredentialsException("Invalid credentials");
        }

    }

    private List<GrantedAuthority> getAuthorities(Role role) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role.toString()));
        return authorities;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
