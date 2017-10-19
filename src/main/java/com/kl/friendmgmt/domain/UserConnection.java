package com.kl.friendmgmt.domain;

import javax.persistence.*;

@Entity
@Table(name = "user_connection")
public class UserConnection {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "connectionid")
    private Integer id;

    @Column(name = "user1")
    private Integer user1;
    @Column(name = "user2")
    private Integer user2;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser1() {
        return user1;
    }

    public void setUser1(Integer user1) {
        this.user1 = user1;
    }

    public Integer getUser2() {
        return user2;
    }

    public void setUser2(Integer user2) {
        this.user2 = user2;
    }
}

