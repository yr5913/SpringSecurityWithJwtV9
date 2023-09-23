package com.simplebank.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        /*
         * Configuration to deny all requests
         * */
//        http.authorizeHttpRequests(request -> request.anyRequest().denyAll())
//                .formLogin(Customizer.withDefaults())
//                .httpBasic(Customizer.withDefaults());

        /*
         Configuration to permit all requests
         */
//        http.authorizeHttpRequests
//        (request -> request.anyRequest().permitAll())
//                .formLogin(Customizer.withDefaults())
//                .httpBasic(Customizer.withDefaults());

        /*
         Configuration with proper authentication
         */
        http.authorizeHttpRequests((requests) -> requests.requestMatchers("/account/**", "/cards/**", "/loans/**", "/balance/**").authenticated()
                        .requestMatchers("/contact/**", "/notices/**", "/test/**").permitAll())
                .formLogin(withDefaults())
                .httpBasic(withDefaults());
        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {


        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("admin")
                .authorities("admin").build();
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("12345")
                .authorities("read").build();
        return new InMemoryUserDetailsManager(admin);
    }
}
