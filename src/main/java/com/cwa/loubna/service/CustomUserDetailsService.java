package com.cwa.loubna.service;

import com.cwa.loubna.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.cwa.loubna.repository.UserRepository;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("l'utilisateur avec le nom"+username+" n'existe pas" );
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),Collections.singletonList(new SimpleGrantedAuthority(
                user.getRole() != null ? user.getRole() : "USER"
        )));

    }
}
