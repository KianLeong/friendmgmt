package com.kl.friendmgmt.controller;

import com.kl.friendmgmt.domain.UserSubscription;
import com.kl.friendmgmt.dto.UserInfoDTO;

import com.kl.friendmgmt.json.request.FriendRequest;
import com.kl.friendmgmt.json.request.SenderRequest;
import com.kl.friendmgmt.json.request.SubscriptionRequest;
import com.kl.friendmgmt.json.request.UserRequest;
import com.kl.friendmgmt.json.response.ApiResponse;
import com.kl.friendmgmt.json.response.FriendResponse;
import com.kl.friendmgmt.json.response.RecipientResponse;
import com.kl.friendmgmt.service.UserConnectionService;
import com.kl.friendmgmt.service.UserInfoService;
import com.kl.friendmgmt.service.UserNotificationService;
import com.kl.friendmgmt.service.UserSubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RestApiController {
    @Autowired
    public UserInfoService userinfoservice;

    @Autowired
    public UserConnectionService userconnservice;

    @Autowired
    public UserSubscriptionService usersubscriptionservice;

    @Autowired
    public UserNotificationService usernotificationservice;

    public static final Logger logger = LoggerFactory.getLogger(RestApiController.class);


    @RequestMapping(value = "/createuser", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> createUser(@Valid @RequestBody UserRequest userrequest){
        UserInfoDTO user=new UserInfoDTO();
        user.setEmail(userrequest.getEmail());
        Integer id=userinfoservice.createUser(user);
        logger.info("Created User: {}",id);
        return new ResponseEntity<>(new ApiResponse(true), HttpStatus.OK);
    }


    @RequestMapping(value = "/connectuser", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> connectUser(@RequestBody FriendRequest friendrequest){
        List<String> friendlist=friendrequest.getFriends();

        UserInfoDTO user1=new UserInfoDTO();
        user1.setEmail(friendlist.get(0));

        UserInfoDTO user2=new UserInfoDTO();
        user2.setEmail(friendlist.get(1));

        Integer id=userconnservice.connectUsers(user1,user2);
        logger.info("Created Connection: {}",id);
        //UserInfo saveduser=userinfodao.save(user);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true), HttpStatus.OK);

    }

    @RequestMapping(value="/getconnection", method=RequestMethod.POST)
    public ResponseEntity<FriendResponse> getConnection(@RequestBody UserRequest userrequest){
        UserInfoDTO user=new UserInfoDTO();
        user.setEmail(userrequest.getEmail());
        List<String> userlist=userconnservice.getFriendList(user);

        return new ResponseEntity<FriendResponse>(new FriendResponse(true,userlist,userlist.size()),HttpStatus.OK);
    }

    @RequestMapping(value="/getmutualconnection", method=RequestMethod.POST)
    public ResponseEntity<FriendResponse> getMutualConnection(@RequestBody FriendRequest friendrequest){
        List<String> friendlist=friendrequest.getFriends();

        UserInfoDTO user1=new UserInfoDTO();
        user1.setEmail(friendlist.get(0));

        UserInfoDTO user2=new UserInfoDTO();
        user2.setEmail(friendlist.get(1));

        List<String> userlist=userconnservice.getMutualFriendList(user1,user2);


        return new ResponseEntity<FriendResponse>(new FriendResponse(true,userlist,userlist.size()),HttpStatus.OK);
    }

    @RequestMapping(value = "/subscribeuser", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> subscribeUser(@RequestBody SubscriptionRequest subscriptionrequest){

        UserInfoDTO user1=new UserInfoDTO();
        user1.setEmail(subscriptionrequest.getRequestor());

        UserInfoDTO user2=new UserInfoDTO();
        user2.setEmail(subscriptionrequest.getTarget());

        Integer id=usersubscriptionservice.subscribeUser(user1,user2);
        logger.info("Created Subscription: {}",id);
        //UserInfo saveduser=userinfodao.save(user);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true), HttpStatus.OK);

    }

    @RequestMapping(value = "/blockuser", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> blockUser(@RequestBody SubscriptionRequest subscriptionrequest){

        UserInfoDTO user1=new UserInfoDTO();
        user1.setEmail(subscriptionrequest.getRequestor());

        UserInfoDTO user2=new UserInfoDTO();
        user2.setEmail(subscriptionrequest.getTarget());

        Integer id=usersubscriptionservice.blockUser(user1,user2);
        logger.info("Block Subscription: {}",id);
        //UserInfo saveduser=userinfodao.save(user);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true), HttpStatus.OK);

    }

    @RequestMapping(value = "/sendnotification", method = RequestMethod.POST)
    public ResponseEntity<RecipientResponse> sendNotification(@RequestBody SenderRequest senderrequest){
        UserInfoDTO user=new UserInfoDTO();
        user.setEmail(senderrequest.getSender());

        List<String> userlist=usernotificationservice.notifyUsers(user,senderrequest.getText());


        return new ResponseEntity<RecipientResponse>(new RecipientResponse(true,userlist),HttpStatus.OK);
    }

    @RequestMapping(value = "/helloworld", method = RequestMethod.GET)
    public ResponseEntity<String> testMethod(){
        logger.info("wow made a call");
        return new ResponseEntity<String>("Hello World", HttpStatus.OK);
    }

}

