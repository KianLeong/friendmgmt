create database friends;
CREATE USER 'user'@'%' IDENTIFIED BY 'passw0rd';
GRANT ALL PRIVILEGES ON friends.* TO 'user'@'%';

use friends;

drop table if exists user_info;
CREATE TABLE user_info (
  userinfoid INT NOT NULL AUTO_INCREMENT,
  email VARCHAR(100) NOT NULL COMMENT 'email',
  UNIQUE KEY `EMAIL_UNIQUE` (email),
  PRIMARY KEY (userinfoid)
) ;

drop table if exists user_connection;
CREATE TABLE user_connection (
  connectionid INT NOT NULL AUTO_INCREMENT,
  user1 INT NOT NULL COMMENT 'first email id',
  user2  INT NOT NULL COMMENT 'second email id',
  PRIMARY KEY (connectionid),
  UNIQUE KEY `CONNECTION_UNIQUE`(user1,user2)
);

drop table if exists user_subscription;
CREATE TABLE user_subscription (
  subscriptionid INT NOT NULL AUTO_INCREMENT,
  requestor INT NOT NULL COMMENT 'requestor email id',
  target  INT NOT NULL COMMENT 'target email id',
  isblocked TINYINT DEFAULT 1 COMMENT '0:subscribed|1:blocked',
  PRIMARY KEY (subscriptionid),
  UNIQUE KEY `SUBSCRIPTION_UNIQUE`(requestor,target)
) ;