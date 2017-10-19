package com.kl.friendmgmt.json.response;

import java.util.List;

public class FriendResponse extends ApiResponse{
    private List<String> friends;
    private int count;

    public FriendResponse(boolean success, List<String> friends, int count) {
        super(success);
        this.friends = friends;
        this.count = count;
    }

    public List<String> getFriends() {
        return friends;
    }

    public void setFriends(List<String> friends) {
        this.friends = friends;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
