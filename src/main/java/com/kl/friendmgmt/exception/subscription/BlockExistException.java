package com.kl.friendmgmt.exception.subscription;

public class BlockExistException extends RuntimeException{
    public BlockExistException(String request,String target) {
        super(request+" already blocked "+target);

    }
}
