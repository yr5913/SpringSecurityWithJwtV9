//package com.simplebank.config;
//
//import com.simplebank.entity.Customer;
//import com.simplebank.repository.CustomerRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class SimpleBankUserDetails implements UserDetailsService {
//
//
//    @Autowired
//    CustomerRepository customerRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//        User user = null;
//        List<Customer> customers = customerRepository.findByEmail(username);
//        if (customers.isEmpty()) {
//            throw new UsernameNotFoundException("Cannot find the user " + username);
//        } else {
//            user = new User(customers.get(0).getEmail(), customers.get(0).getPwd(), List.of(new SimpleGrantedAuthority(customers.get(0).getRole())));
//
//        }
//        return user;
//    }
//}
