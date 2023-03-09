package com.project.therapy.testproject.service;

import com.project.therapy.testproject.dto.UserRegistrationDTO;
import com.project.therapy.testproject.model.Role;
import com.project.therapy.testproject.model.User;
import com.project.therapy.testproject.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User saveUser(UserRegistrationDTO userRegistrationDetails) {
            User user = new User();
            user.setUsername(userRegistrationDetails.getUsername());
            user.setPassword(bCryptPasswordEncoder.encode(userRegistrationDetails.getPassword()));
            user.setEmail(userRegistrationDetails.getEmail());
            user.setPhone(userRegistrationDetails.getPhone());
            user.setRoles(List.of(new Role(userRegistrationDetails.getRole())));
            user.setFirstTime(true);
            return userRepo.save(user);
    }

    public User findByUsername(String username) {
        return userRepo.findByUsername(username);
    }
    public void updateUser(User user) {
        userRepo.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        if(user == null)
            throw new UsernameNotFoundException("Invalid username or password");
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
