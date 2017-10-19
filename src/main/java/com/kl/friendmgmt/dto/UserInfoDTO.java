package com.kl.friendmgmt.dto;


import java.io.Serializable;

public class UserInfoDTO implements Serializable{
    private Integer id;
    private String email;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
