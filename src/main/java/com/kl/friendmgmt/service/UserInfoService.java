package com.kl.friendmgmt.service;

import com.kl.friendmgmt.dao.UserInfoDAO;
import com.kl.friendmgmt.domain.UserInfo;
import com.kl.friendmgmt.dto.UserInfoDTO;
import com.kl.friendmgmt.exception.userinfo.UserInfoExistException;
import com.kl.friendmgmt.exception.userinfo.UserInfoNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService {
    @Autowired
    private UserInfoDAO userinfodao;

    public Integer createUser(UserInfoDTO userdto){
        UserInfo userinfo1=userinfodao.findByEmail(userdto.getEmail());
        if (userinfo1!=null){
            throw new UserInfoExistException(userdto.getEmail());
        }
        UserInfo user = new UserInfo();
        user.setEmail(userdto.getEmail());
        userinfodao.save(user);
        return user.getId();

    }

    public int getUserID(UserInfoDTO userdto) {
        UserInfo userinfo=userinfodao.findByEmail(userdto.getEmail());
        if (userinfo==null){
            throw new UserInfoNotExistException(userdto.getEmail());
        }
        return userinfo.getId();
    }
}
