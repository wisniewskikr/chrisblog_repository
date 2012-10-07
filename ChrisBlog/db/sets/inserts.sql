INSERT INTO users (ID, USERNAME,PASSWORD, ENABLED)
VALUES (1, 'chris', 'e50b3c5d55c6565bc0dcfbedae036e1b0dcb1f92', TRUE);

GO

INSERT INTO user_roles (ID, USER_ID,AUTHORITY)
VALUES (1, 1, 'ROLE_USER');

GO

INSERT INTO articles (ID, UNIQUE_NAME, TITLE, DESCRIPTION, CONTENT, PAGES_COUNT, CREATION_DATE, AUTHOR, DEMO_NAME, EXAMPLE_FILE_NAME, SOURCE_FILE_NAME)
VALUES (1, 'hello_world_servlets', 'Hello World Servlets', 'helloWorldServletsJsp', 'helloWorldServletsJsp', 4, '2012-01-04 12:00:00', 'Chris', 'HelloWorldServlets', 'HelloWorldServlets.war', 'HelloWorldServlets.zip');

GO