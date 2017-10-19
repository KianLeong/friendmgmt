package com.kl.friendmgmt.dao;


import com.kl.friendmgmt.domain.UserConnection;
import com.kl.friendmgmt.domain.UserInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserConnectionDAO extends CrudRepository<UserConnection, Long> {
    UserConnection findByUser1AndUser2(int user1,int user2);
    @Query(nativeQuery=true,value=
            "select email from user_info where userinfoid in " +
                    "(select user1 as frienduserid from user_connection where user2=:userid" +
                    " union select user2 as frienduserid from user_connection where user1=:userid)")
    List<String> getFriendList(@Param("userid") int userid);

    @Query(nativeQuery=true,value=
            "select email from user_info where userinfoid in " +
                    "(select user1 as frienduserid from user_connection where user2=:userid1" +
                    " union select user2 as frienduserid from user_connection where user1=:userid1)" +
                    " and userinfoid in  (select user1 as frienduserid from user_connection where user2=:userid2" +
                    " union select user2 as frienduserid from user_connection where user1=:userid2)"+
                    " and (userinfoid != :userid1 or userinfoid != :userid2)"
    )
    List<String> getMutualFriendList(@Param("userid1") int userid1, @Param("userid2") int userid2);
}
