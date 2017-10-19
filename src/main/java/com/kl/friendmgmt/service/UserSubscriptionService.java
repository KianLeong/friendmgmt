package com.kl.friendmgmt.service;

import com.kl.friendmgmt.dao.UserInfoDAO;
import com.kl.friendmgmt.dao.UserSubscriptionDAO;
import com.kl.friendmgmt.domain.UserConnection;
import com.kl.friendmgmt.domain.UserInfo;
import com.kl.friendmgmt.domain.UserSubscription;
import com.kl.friendmgmt.dto.UserInfoDTO;
import com.kl.friendmgmt.exception.subscription.BlockExistException;
import com.kl.friendmgmt.exception.subscription.SubscriptionExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserSubscriptionService {
    @Autowired
    private UserInfoService userinfoservice;
    @Autowired
    private UserSubscriptionDAO usersubdao;

    public Integer subscribeUser(UserInfoDTO userdto1, UserInfoDTO userdto2) {
        UserSubscription usersub=updateSubscription(userdto1,userdto2,false);
        if (usersub==null){
            throw new SubscriptionExistException(userdto1.getEmail(),userdto2.getEmail());
        }
        usersubdao.save(usersub);
        return usersub.getId();
    }

    public Integer blockUser(UserInfoDTO userdto1, UserInfoDTO userdto2) {

        UserSubscription usersub=updateSubscription(userdto1,userdto2,true);

        if (usersub==null){
            throw new BlockExistException(userdto1.getEmail(),userdto2.getEmail());
        }
        usersubdao.save(usersub);
        return usersub.getId();
    }



    private UserSubscription updateSubscription(UserInfoDTO userdto1, UserInfoDTO userdto2, boolean block){
        int userid1=userinfoservice.getUserID(userdto1);

        int userid2=userinfoservice.getUserID(userdto2);

        UserSubscription usersub=usersubdao.findDistinctByRequestorAndTarget(userid1,userid2);
        if (usersub ==null) {
            usersub = new UserSubscription();
            usersub.setRequestor(userid1);
            usersub.setTarget(userid2);
            usersub.setBlocked(block);
        }
        else {
            if ((block && usersub.isBlocked())||(!block && !usersub.isBlocked())) {
                return null;
            }
            else{
                usersub.setBlocked(block);
            }
        }
        return usersub;
    }
}
