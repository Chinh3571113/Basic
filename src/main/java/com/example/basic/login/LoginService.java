package com.example.basic.login;

import com.example.basic.services.JwtService;
import com.example.basic.user.User;
import com.example.basic.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;


@Slf4j
@Service
public class LoginService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;

    public String login(String username, String password) {
        String token = "";
        Optional<User> user = userRepository.findByUsername(username);
//        String encodedPassword = passwordEncoder.encode("12345678");
//        String base64Password = Base64.getEncoder().encodeToString(encodedPassword.getBytes());

        if (user.isPresent())  {
            User userEntity = user.get();
            if(passwordEncoder.matches(password, userEntity.getPassword())) {
                token = jwtService.generateToken(user.get().getId(), user.get().getUsername());
            }
        }
        else throw new RuntimeException("User not found");
        return token;
    }
}
