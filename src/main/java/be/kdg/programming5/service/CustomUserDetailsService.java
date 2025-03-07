package be.kdg.programming5.service;

import be.kdg.programming5.repository.UserRepository;
import be.kdg.programming5.domain.User;
import be.kdg.programming5.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
        private final UserRepository userRepository;

        @Autowired
        public CustomUserDetailsService(UserRepository userRepository) {
            this.userRepository = userRepository;
        }

        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            return new CustomUserDetails(user);
        }
    }

