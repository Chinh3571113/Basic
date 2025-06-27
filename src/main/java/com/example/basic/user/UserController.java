package com.example.basic.user;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/v1/user")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "User Controller", description = "Quản lý người dùng")
public class UserController {
    private final UserServiceImpl userServiceImpl;
    @Autowired
    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }


    @GetMapping("/getUsers")
    @Operation(summary = "Lấy danh sách người dùng", description = "Trả về danh sách tất cả người dùng")
    public List<String> getUsers() {
        return Arrays.asList("Laptop", "Smartphone", "Tablet");
    }

    @GetMapping("/{id}")
    @Operation(summary = "Lấy thông tin người dùng theo ID")
    public User getUser(@PathVariable Long id) {
        return userServiceImpl.getUser(id);
    }
}
