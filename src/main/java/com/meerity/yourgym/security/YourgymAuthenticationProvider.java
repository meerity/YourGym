package com.meerity.yourgym.security;

import com.meerity.yourgym.model.entity.Person;
import com.meerity.yourgym.model.entity.Role;
import com.meerity.yourgym.service.PersonService;
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

    private final PasswordEncoder passwordEncoder;
    private final PersonService personService;

    @Autowired
    public YourgymAuthenticationProvider(PasswordEncoder passwordEncoder, PersonService personService) {
        this.passwordEncoder = passwordEncoder;
        this.personService = personService;
    }


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String emailOrPhone = authentication.getName();
        String password = authentication.getCredentials().toString();
        Person person = personService.findByEmailOrPhoneNumber(emailOrPhone);
        if (person != null && passwordEncoder.matches(password, person.getPsw())) {
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
