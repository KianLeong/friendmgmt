package com.kl.friendmgmt.exception.userinfo;

public class UserInfoExistException extends RuntimeException{
    public UserInfoExistException(String email) {
        super(email+" already exists in system");

    }
}
