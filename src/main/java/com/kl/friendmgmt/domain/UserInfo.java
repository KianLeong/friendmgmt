package com.kl.friendmgmt.domain;

import javax.persistence.*;

@Entity
@Table(name="user_info")
public class UserInfo {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="userinfoid")
    private Integer id;
    @Column(name="email")
    private String email;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean equals(Object other) {
        if (other == this) return true;
        if (other == null) return false;
        if (getClass() != other.getClass()) return false;
        UserInfo person = (UserInfo)other;
        return (
                (id==person.id || (id != null && person.id!=null && id.equals(person.id))) &&
                        (email == person.email ||(email != null && email.equals(person.email)))
        );
    }


}
