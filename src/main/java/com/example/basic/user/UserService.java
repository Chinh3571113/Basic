package com.example.basic.user;

import com.example.basic.roles.Role;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<User> getUsers();
    User getUser(Long id);
}
