package com.kl.friendmgmt.exception.connection;

public class ConnectionBlockedException extends RuntimeException{
    public ConnectionBlockedException(String email1, String email2){
        super(email1+" and "+email2+" are not allowed to be connected");
    }
}
