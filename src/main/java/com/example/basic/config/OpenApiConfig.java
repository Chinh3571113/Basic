package com.example.basic.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(title = "My API", version = "v1", description = "API sử dụng JWT cho bảo mật")
)
//@SecurityScheme(
//        name = "bearerAuth", // tên scheme, dùng để tham chiếu ở @SecurityRequirement
//        type = SecuritySchemeType.HTTP,
//        scheme = "bearer",
//        bearerFormat = "JWT" // chỉ định rằng token có định dạng JWT
//)
public class OpenApiConfig {
    // Bạn có thể thêm các cấu hình khác nếu cần
}
