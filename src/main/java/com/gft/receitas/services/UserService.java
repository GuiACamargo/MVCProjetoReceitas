package com.gft.receitas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gft.receitas.entities.User;
import com.gft.receitas.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
    public void registrar(User user) {
        userRepository.save(user);
    }

}
