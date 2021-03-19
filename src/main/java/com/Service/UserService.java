package com.Service;

import com.Model.User;
import com.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private long CLIENT_ID_HOLDER;

    /*public boolean create(User client) {
        final long clientId = ++CLIENT_ID_HOLDER;
        client.setId(clientId);
        //client.setPassword(passwordEncoder.encode(client.getPassword()));
        // client.setRoles(Collections.singleton(Role.ADMIN));
        userRepository.save(client);
        return true;
    }*/
    public List<User> readAll() {
        return userRepository.findAll();
    }
    public User login(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user.getPassword().equals(password))
        {
            return user;
        }
        return null;
    }


    public User read(long id) {
        return userRepository.findById(id).orElseThrow();
    }

    /*public User loadUserByUsername(String s) {
        User client1 = userRepository.findByUsername(s);
        return client1;
    }*/
}
