package com.novi.springboottechiteasy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.withDefaults;

//@EnableWebSecurity
//@Configuration
public class SpringSecurityConfig {
//    private final DataSource dataSource;
//
//    public SpringSecurityConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }
//
//    @Bean
//    PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//
//    @Bean
//    protected SecurityFilterChain filter(HttpSecurity http) throws Exception {
//
//        http
//                .authorizeHttpRequests((authz) -> authz
//                        .anyRequest().authenticated()
//                )
//                .httpBasic(withDefaults())
//                .requestMatchers(HttpMethod.GET, "/info").hasRole("USER")
//                .requestMatchers("/users/**").hasAnyRole("ADMIN", "USER")
//                .requestMatchers("/admins").hasAuthority("ROLE_ADMIN")
//                .requestMatchers(HttpMethod.DELETE, "/users/{id}").hasRole("ADMIN")
//                .requestMatchers("/authenticate").permitAll()
//                .anyRequest().denyAll()
//                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//        return http.build();
//    }
//
//    @Bean
//    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
//        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
//        authenticationManagerBuilder.jdbcAuthentication().dataSource(dataSource)
//                .usersByUsernameQuery("SELECT username, password, enabled" +
//                        " FROM users" +
//                        " WHERE username=?")
//                .authoritiesByUsernameQuery("SELECT username, authority" +
//                        " FROM authorities " +
//                        " WHERE username=?");
//        return authenticationManagerBuilder.build();
//    }
}

