package com.kl.friendmgmt.domain;

import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name="user_subscription")
public class UserSubscription {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "subscriptionid")
    private Integer id;

    @Column(name = "requestor")
    private Integer requestor;
    @Column(name = "target")
    private Integer target;

    @Column(name = "isblocked", nullable = false)
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean isBlocked;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRequestor() {
        return requestor;
    }

    public void setRequestor(Integer requestor) {
        this.requestor = requestor;
    }

    public Integer getTarget() {
        return target;
    }

    public void setTarget(Integer target) {
        this.target = target;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }
}
