package com.kl.friendmgmt.exception.subscription;

public class SubscriptionExistException extends RuntimeException {
    public SubscriptionExistException(String request,String target) {
        super(request+" already subscribed to "+target);

    }
}
