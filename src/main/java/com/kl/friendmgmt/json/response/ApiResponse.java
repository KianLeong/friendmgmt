package com.kl.friendmgmt.json.response;

import java.io.Serializable;

public class ApiResponse implements Serializable {
    protected boolean success=false;

    public ApiResponse(boolean success){
        this.success=success;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
