package com.kl.friendmgmt.json.response;

import java.util.List;

public class RecipientResponse extends ApiResponse {
    private List<String> recipients;

    public RecipientResponse(boolean success, List<String> recipients) {
        super(success);
        this.recipients=recipients;

    }

    public List<String> getRecipients() {
        return recipients;
    }

    public void setRecipients(List<String> recipients) {
        this.recipients = recipients;
    }
}
