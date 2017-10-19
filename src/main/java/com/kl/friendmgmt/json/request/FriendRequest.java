package com.kl.friendmgmt.json.request;

import java.util.List;

public class FriendRequest extends ApiRequest{

    private List<String> friends;

    public List<String> getFriends() {
        return friends;
    }

    public void setFriends(List<String> friends) {
        this.friends = friends;
    }
}
