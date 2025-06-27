package com.example.basic.services;


import com.example.basic.entities.Blacklisted;
import com.example.basic.repositories.BlacklistedRepository;
import com.example.basic.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;


@Slf4j
@Service
public class BlacklistedService {
    @Autowired
    private BlacklistedRepository blacklistedRepository;
    @Autowired
    private JwtService jwtService;

    public BaseResponse create(String token) {
        BaseResponse response = new BaseResponse();
        try {
            // Check token is exist or not
            boolean existedToken = blacklistedRepository.existsByToken(token);
            if(existedToken) {
                response.setCode(404);
                response.setMessage("Token is already existed");
                return response;
            }
            // Get userId from token
            Long userId = jwtService.extractUserId(token);
            // Get expiryDate from token
            Date expiryDate = jwtService.extractExpiration(token);
            // Save token into db
            Blacklisted blacklisted = new Blacklisted();
            blacklisted.setToken(token);
            blacklisted.setUserId(userId);
            blacklisted.setExpiryDate(expiryDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            blacklistedRepository.save(blacklisted);
            response.setCode(200);
            response.setMessage("Success");

            return response;
        }catch (Exception e) {
            response.setCode(404);
            response.setMessage("Network error");
            return response;
        }
    }


}
