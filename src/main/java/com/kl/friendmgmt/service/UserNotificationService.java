package com.kl.friendmgmt.service;

import com.kl.friendmgmt.dao.UserConnectionDAO;
import com.kl.friendmgmt.dao.UserSubscriptionDAO;
import com.kl.friendmgmt.dto.UserInfoDTO;
import com.kl.friendmgmt.exception.userinfo.UserInfoNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserNotificationService {
    @Autowired
    private UserInfoService userinfoservice;

    @Autowired
    private UserConnectionDAO userconnectiondao;

    @Autowired
    private UserSubscriptionDAO usersubscriptiondao;

    public List<String> notifyUsers(UserInfoDTO userdto,String message) {
        int userid=userinfoservice.getUserID(userdto);

        List<String> notifiedlist=usersubscriptiondao.getNotificationList(userid);

        List<String> friendlist=userconnectiondao.getFriendList(userid);
        List<String> mentionedlist=getMentionedList(message);

        List<String> combinedlist = Stream.concat(friendlist.stream(), mentionedlist.stream())
                .distinct()
                .collect(Collectors.toList());

        combinedlist=Stream.concat(combinedlist.stream(), notifiedlist.stream())
                .distinct()
                .collect(Collectors.toList());

        List<String> blockedlist=usersubscriptiondao.getBlockedList(userid);

        combinedlist.removeAll(blockedlist);

        return combinedlist;


    }

    private List<String> getMentionedList(String message) {
        Set<String> emailset=new HashSet<>();
        Matcher m = Pattern.compile("[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+").matcher(message);

        while(m.find()){
            emailset.add(m.group());
        }

        Iterator<String> iterator = emailset.iterator();
        while(iterator.hasNext()) {
            String email = iterator.next();
            try {
                UserInfoDTO userdto=new UserInfoDTO();
                userdto.setEmail(email);
                userinfoservice.getUserID(userdto);

            } catch(UserInfoNotExistException ex){
                iterator.remove();
            }
        }

        List<String> emaillist=new ArrayList<String>();
        emaillist.addAll(emailset);
        return emaillist;
    }
}
