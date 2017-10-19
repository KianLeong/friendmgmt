package com.kl.friendmgmt.json.response;

import java.util.List;

public class ApiErrorResponse extends ApiResponse{
    private List<String> message;

    public ApiErrorResponse(List<String> message){
        super(false);
        this.message=message;
    }

    public List<String> getMessage() {
        return message;
    }

    public void setMessage(List<String> message) {
        this.message = message;
    }
}
