package com.Controllers;

import com.Model.User;
import com.Security.JWT.JwtTokenProvider;
import com.Service.UserService;
import com.dto.AuthenticationRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class RController {
    @Autowired
    UserService userService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtTokenProvider jwtTokenProvider;
    @GetMapping("/user")
    public String User(@RequestParam(value = "username") String name, @RequestParam(value = "password") String password)
    {
        User user = userService.login(name, password);
        return user.toString();
    }
    @PostMapping(value = "/new", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@RequestBody User client)  throws ParseException
    {
        userService.create(client);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping("/loging")
    public ResponseEntity login(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        User user = userService.loadUserByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User with username: " + username + " not found");
        }

        String token = jwtTokenProvider.createToken(username, user.getRoles());

        Map<Object, Object> response = new HashMap<>();
        response.put("username", username);
        response.put("token", token);
        userService.CreateToken(token, user);
        return ResponseEntity.ok(response);
    }
}
