package com.alkemy.disney.disney.auth.service;

import com.alkemy.disney.disney.auth.dto.UserDTO;
import com.alkemy.disney.disney.auth.entity.UserEntity;
import com.alkemy.disney.disney.auth.repository.UserRepository;
import com.alkemy.disney.disney.mail.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsCustomService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    public UserDetailsCustomService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

     @Autowired
    private MailService mailService;


    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(userName);

        if (userEntity == null){
            throw new UsernameNotFoundException("Username or password not found");
        }
        //en la coleccion pasaría los roles
        return new User(userEntity.getUsername(), userEntity.getPassword(), Collections.emptyList());
    }

    public boolean save(UserDTO userDTO){
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userDTO.getUsername());
        userEntity.setPassword(userDTO.getPassword());
        userEntity = this.userRepository.save(userEntity);

        if (userEntity != null){
            mailService.sendWelcomeMailTo(userEntity.getUsername());
        }

        return userEntity != null;

    }
}
