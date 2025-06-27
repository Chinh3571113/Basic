package com.example.basic.request;

import lombok.Data;
import lombok.NonNull;
import org.antlr.v4.runtime.misc.NotNull;

@Data
public class BlacklistedRequest {
    @NotNull
    private String token;

    public @NotNull String getToken() {
        return token;
    }
}
