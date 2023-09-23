package com.simplebank.config;

import com.simplebank.entity.Customer;
import com.simplebank.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SimpleBankAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        List<Customer> customers = customerRepository.findByEmail(username);
        if (customers.isEmpty()) {
            throw new BadCredentialsException("User not found");
        } else {
            if (passwordEncoder.matches(password, customers.get(0).getPwd())) {
                return new UsernamePasswordAuthenticationToken(username, password, List.of(new SimpleGrantedAuthority(customers.get(0).getRole())));
            } else {
                throw new BadCredentialsException("Invalid Credentials");
            }
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
