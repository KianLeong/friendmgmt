package com.kl.friendmgmt.exception.userinfo;

public class UserInfoNotExistException extends RuntimeException{
    public UserInfoNotExistException(String email) {
        super(email+" does not exist in system");

    }
}
