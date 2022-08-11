package com.gft.receitas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.gft.receitas.entities.User;
import com.gft.receitas.repositories.UserRepository;

@Component
public class StartApplication implements CommandLineRunner {
    @Autowired
    private UserRepository repository;
    @Transactional
    @Override
    public void run(String... args) throws Exception {
        User user = repository.findByUsername("admin@gft.com");
        if(user==null){
            user = new User();
            user.setName("admin@gft.com");
            user.setUsername("admin@gft.com");
            user.setPassword("Gft@1234");
            user.getRoles().add("admin");
            repository.save(user);
        }
        user = repository.findByUsername("usuario@gft.com");
        if(user ==null){
            user = new User();
            user.setName("usuario@gft.com");
            user.setUsername("usuario@gft.com");
            user.setPassword("Gft@1234");
            user.getRoles().add("user");
            repository.save(user);
        }
    }
}
