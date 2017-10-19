package com.kl.friendmgmt.dao;

import com.kl.friendmgmt.domain.UserSubscription;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserSubscriptionDAO extends CrudRepository<UserSubscription, Long> {
   UserSubscription findDistinctByRequestorAndTarget(int requestor, int target);

    @Query(nativeQuery=true,value=
            "select email from user_info where userinfoid in " +
                                "(select requestor  from user_subscription where target=:userid" +
                                " and isblocked=0)")
    List<String> getNotificationList(@Param("userid") int userid);

    @Query(nativeQuery=true,value=
            "select email from user_info where userinfoid in " +
                    "(select requestor  from user_subscription where target=:userid" +
                    " and isblocked=1)")
    List<String> getBlockedList(@Param("userid") int userid);

    @Query(nativeQuery=true,value=
            "select case when (count(*) >0) then 'true' else 'false' end " +
                    "from user_subscription where (requestor=:userid1 and target=:userid2)" +
                    " or (requestor=:userid2 and target=:userid1) and isblocked=1" )
    boolean isConnectionBlocked(@Param("userid1") int userid1, @Param("userid2") int userid2);
}
