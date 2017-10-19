package com.kl.friendmgmt.json.request;

import org.hibernate.validator.constraints.NotBlank;

public class UserRequest extends ApiRequest{
    @NotBlank(message = "Email must not be blank!")
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
