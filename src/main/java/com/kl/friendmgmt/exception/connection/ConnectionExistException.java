package com.kl.friendmgmt.exception.connection;

public class ConnectionExistException extends RuntimeException {
    public ConnectionExistException(String email1, String email2){
        super(email1+" and "+email2+" are already connected");
    }
}
