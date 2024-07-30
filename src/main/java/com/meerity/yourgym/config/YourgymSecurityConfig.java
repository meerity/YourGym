package com.meerity.yourgym.config;

import com.meerity.yourgym.security.CustomAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class YourgymSecurityConfig {

    private final DataSource dataSource;
    private final UserDetailsService userDetailsService;
    private final CustomAuthenticationSuccessHandler cash;

    @Autowired
    public YourgymSecurityConfig(CustomAuthenticationSuccessHandler cash, DataSource dataSource, UserDetailsService userDetailsService) {
        this.cash = cash;
        this.dataSource = dataSource;
        this.userDetailsService = userDetailsService;
    }

    @Bean
    SecurityFilterChain basicSecurityConfig(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.ignoringRequestMatchers("/feedback/saveMsg", "/profile/**", "/operator/**")).
                authorizeHttpRequests(requests -> requests
                .requestMatchers("/assets/**", "/", "/home", "/features", "/feedback/**", "/login", "/logout", "/error", "/register", "/do-register").permitAll()
                .requestMatchers("/payment").authenticated()
                .requestMatchers("/profile/**").hasRole("CLIENT")
                .requestMatchers("/operator/**").hasAnyRole("ADMIN", "OPERATOR"))
                .formLogin(httpSecurityFormLoginConfigurer -> httpSecurityFormLoginConfigurer
                        .loginPage("/login")
                        .successHandler(cash)
                        .failureUrl("/login?error=true").permitAll())
                .logout(httpSecurityLogoutConfigurer -> httpSecurityLogoutConfigurer
                        .logoutSuccessUrl("/login?logout=true")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID", "remember-me")
                        .permitAll())
                .rememberMe(httpSecurityRememberMeConfigurer -> httpSecurityRememberMeConfigurer
                        .tokenRepository(persistentTokenRepository())
                        .userDetailsService(userDetailsService)
                        .tokenValiditySeconds(1209600))
                .httpBasic(withDefaults());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        return tokenRepository;
    }


}
