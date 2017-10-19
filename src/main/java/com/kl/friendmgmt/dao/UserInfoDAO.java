package com.kl.friendmgmt.dao;

import com.kl.friendmgmt.domain.UserInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserInfoDAO extends CrudRepository<UserInfo, Long> {
   UserInfo findByEmail(String email);

}
