package com.simplebank.config;


import com.simplebank.filter.CsrfCookieFilter;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

        CsrfTokenRequestAttributeHandler csrfHandler = new CsrfTokenRequestAttributeHandler();
        csrfHandler.setCsrfRequestAttributeName("_csrf");

        /*
         Configuration with proper authentication and added CORS
         */
        http.authorizeHttpRequests((requests) ->
                        requests
//                                .requestMatchers("/account/get").hasAuthority("VIEWACCOUNT")
//                                .requestMatchers("/loans/get").hasAuthority("VIEWLOANS")
//                                .requestMatchers("/balance/get").hasAnyAuthority("VIEWACCOUNT","VIEWBALANCE")
//                                .requestMatchers("/cards/get").hasAuthority("VIEWCARDS")
                                .requestMatchers("/account/get").hasRole("USER")
                                .requestMatchers("/loans/get").hasRole("USER")
                                .requestMatchers("/balance/get").hasAnyRole("ADMIN", "USER")
                                .requestMatchers("/cards/get").hasRole("USER")
                                .requestMatchers("/customer/login").authenticated()
                                .requestMatchers("/contact/**", "/notices/**", "/test/**", "/customer/register").permitAll())
                .formLogin(withDefaults())
                .httpBasic(withDefaults())
                // session management for UI
                .securityContext(httpSecuritySecurityContextConfigurer ->
                        httpSecuritySecurityContextConfigurer.requireExplicitSave(false))
                .sessionManagement(httpSecuritySessionManagementConfigurer ->
                        httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
                // CSRF config
                .csrf(httpSecurityCsrfConfigurer ->
                        httpSecurityCsrfConfigurer.ignoringRequestMatchers("/contact", "/customer/register")
                                .csrfTokenRequestHandler(csrfHandler)
                                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
                // CORS config
                .cors(httpSecurityCorsConfigurer -> httpSecurityCorsConfigurer.configurationSource(new CorsConfigurationSource() {
                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                        CorsConfiguration corsConfiguration = new CorsConfiguration();
                        corsConfiguration.setAllowedOrigins(List.of("http://localhost:4200"));
                        corsConfiguration.setAllowedMethods(List.of("*"));
                        corsConfiguration.setAllowCredentials(true);
                        corsConfiguration.setAllowedHeaders(List.of("*"));
                        corsConfiguration.setMaxAge(3600L);
                        return corsConfiguration;
                    }
                }));
        return http.build();
    }


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
