package com.meerity.yourgym.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class YourgymSecurityConfig {

    @Bean
    SecurityFilterChain basicSecurityConfig(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.ignoringRequestMatchers("/feedback/saveMsg")).
                authorizeHttpRequests(requests -> requests
                .requestMatchers("/assets/**", "/", "/home", "/features", "/feedback/**", "/login", "/logout", "/error", "/register", "/do-register").permitAll()
                .requestMatchers("/profile").authenticated()
                .requestMatchers("/operator/**").hasAnyRole("ADMIN", "OPERATOR"))
                .formLogin(httpSecurityFormLoginConfigurer -> httpSecurityFormLoginConfigurer.loginPage("/login").defaultSuccessUrl("/profile").failureUrl("/login?error=true").permitAll())
                .logout(httpSecurityLogoutConfigurer -> httpSecurityLogoutConfigurer.logoutSuccessUrl("/login?logout=true").invalidateHttpSession(true).permitAll())
                .httpBasic(withDefaults());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
