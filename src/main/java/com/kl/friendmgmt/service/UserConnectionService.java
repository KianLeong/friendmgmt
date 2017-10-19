package com.kl.friendmgmt.service;

import com.kl.friendmgmt.dao.UserConnectionDAO;
import com.kl.friendmgmt.dao.UserSubscriptionDAO;
import com.kl.friendmgmt.domain.UserConnection;
import com.kl.friendmgmt.domain.UserInfo;
import com.kl.friendmgmt.dto.UserInfoDTO;
import com.kl.friendmgmt.exception.connection.ConnectionBlockedException;
import com.kl.friendmgmt.exception.connection.ConnectionExistException;
import com.kl.friendmgmt.exception.userinfo.UserInfoExistException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserConnectionService {
    public static final Logger logger = LoggerFactory.getLogger(UserConnectionService.class);
    @Autowired
    private UserInfoService userinfoservice;

    @Autowired
    private UserConnectionDAO userconndao;

    @Autowired
    private UserSubscriptionDAO usersubdao;

    public Integer connectUsers(UserInfoDTO userdto1, UserInfoDTO userdto2) {
        int userid1=userinfoservice.getUserID(userdto1);
        int userid2=userinfoservice.getUserID(userdto2);

        if (usersubdao.isConnectionBlocked(userid1,userid2)){
            throw new ConnectionBlockedException(userdto1.getEmail(),userdto2.getEmail());
        }
        UserConnection userconn=new UserConnection();
        if (userid1<userid2) {
            userconn.setUser1(userid1);
            userconn.setUser2(userid2);
        }else{
            userconn.setUser1(userid2);
            userconn.setUser2(userid1);
        }
        UserConnection userconn1=userconndao.findByUser1AndUser2(userconn.getUser1(),userconn.getUser2());
        if (userconn1!=null){
            throw new ConnectionExistException(userdto1.getEmail(),userdto2.getEmail());
        }
        userconndao.save(userconn);

        return userconn.getId();
    }


    public List<String> getFriendList(UserInfoDTO userdto){
        int userid=userinfoservice.getUserID(userdto);

        List<String> friendlist=userconndao.getFriendList(userid);


        return friendlist;
    }

    public List<String> getMutualFriendList(UserInfoDTO userdto1, UserInfoDTO userdto2) {
        int userid1=userinfoservice.getUserID(userdto1);
        int userid2=userinfoservice.getUserID(userdto2);

        List<String> friendlist=userconndao.getMutualFriendList(userid1,userid2);

        return friendlist;

    }

}
