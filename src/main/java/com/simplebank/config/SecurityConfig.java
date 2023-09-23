package com.simplebank.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
//                .formLogin(withDefaults())
//                .httpBasic(withDefaults());

        /*
         Configuration with proper authentication
         */
        http.authorizeHttpRequests((requests) -> requests.requestMatchers("/account/**", "/cards/**", "/loans/**", "/balance/**").authenticated()
                        .requestMatchers("/contact/**", "/notices/**", "/test/**", "/customer/register").permitAll())
                .formLogin(withDefaults())
                .httpBasic(withDefaults()).csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }

//    @Bean
//    public InMemoryUserDetailsManager userDetailsManager() {
//
//        /*
//       Approach 1:  Using password encoder with each user
//         */
///*
//        UserDetails admin = User.withDefaultPasswordEncoder()
//                .username("admin")
//                .password("admin")
//                .authorities("admin").build();
//        UserDetails user = User.withDefaultPasswordEncoder()
//                .username("user")
//                .password("12345")
//                .authorities("read").build();
//*/
//
//        /*
//       Approach 1:  Using password encoder as a bean to use for all users
//         */
//        UserDetails admin = User.withUsername("admin")
//                .password("admin")
//                .authorities("admin").build();
//        UserDetails user = User.withUsername("user")
//                .password("12345")
//                .authorities("read").build();
//        return new InMemoryUserDetailsManager(admin, user);
//    }


//    @Bean
//    public UserDetailsService userDetailsManager(DataSource dataSource) {
//        return new JdbcUserDetailsManager(dataSource);
//    }

    /**
     * NoOpPasswordEncoder is for only non-prod
     *
     * @return PasswordEncoder
     */
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return NoOpPasswordEncoder.getInstance();
//    }

    /**
     * NoOpPasswordEncoder is for only non-prod
     *
     * @return PasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
