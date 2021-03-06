DROP TABLE IF EXISTS users;

GO

CREATE TABLE users (
  ID BIGINT(20) NOT NULL auto_increment,
  USERNAME VARCHAR(45) NOT NULL,
  PASSWORD VARCHAR(100) NOT NULL,
  ENABLED tinyint(1) NOT NULL,
  PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

GO

DROP TABLE IF EXISTS user_roles;

GO

CREATE TABLE user_roles (
  ID BIGINT(20) NOT NULL auto_increment,
  USER_ID INT(10) UNSIGNED NOT NULL,
  AUTHORITY VARCHAR(45) NOT NULL,
  PRIMARY KEY (ID),
  KEY FK_user_roles (USER_ID)
--  CONSTRAINT FK_user_roles FOREIGN KEY (USER_ID) REFERENCES users (USER_ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

GO