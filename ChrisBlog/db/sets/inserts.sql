INSERT INTO mkyongdb.users (USER_ID, USERNAME,PASSWORD, ENABLED)
VALUES (1, 'admin', 'admin', TRUE);

GO

INSERT INTO mkyongdb.user_roles (USER_ROLE_ID, USER_ID,AUTHORITY)
VALUES (1, 1, 'ROLE_USER');

GO