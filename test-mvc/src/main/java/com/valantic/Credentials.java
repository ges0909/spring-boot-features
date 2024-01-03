package com.valantic;

import jakarta.validation.constraints.NotBlank;

public class Credentials {
    @NotBlank(message = "Darf nicht leer sein.")
    private String username;

    @NotBlank(message = "{password.not_empty}")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
