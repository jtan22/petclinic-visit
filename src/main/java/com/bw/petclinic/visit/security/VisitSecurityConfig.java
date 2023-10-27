package com.bw.petclinic.visit.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class VisitSecurityConfig {

//    @Bean
    public UserDetailsManager inMemmoryUserDetailsManager() {
        UserDetails john = User.builder().username("john").password("{noop}john").roles("USER").build();
        UserDetails mary = User.builder().username("mary").password("{noop}mary").roles("USER", "ADMIN").build();
        return new InMemoryUserDetailsManager(john, mary);
    }

    @Bean
    public UserDetailsManager jdbcUserDetailsManager(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(customizer -> customizer
                    .requestMatchers(HttpMethod.GET, "/actuator").authenticated()
                    .requestMatchers(HttpMethod.GET, "/actuator/**").authenticated()
                    .requestMatchers(HttpMethod.GET, "/visits/pet/**").hasRole("USER")
                    .requestMatchers(HttpMethod.POST, "/visits/visit").hasRole("ADMIN"))
                .httpBasic(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }
}
