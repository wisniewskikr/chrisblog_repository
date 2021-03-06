INSERT INTO users (ID, USERNAME,PASSWORD, ENABLED)
VALUES (1, 'chris', 'e50b3c5d55c6565bc0dcfbedae036e1b0dcb1f92', TRUE);

GO

INSERT INTO users (ID, USERNAME,PASSWORD, ENABLED)
VALUES (2, 'test', '9bc34549d565d9505b287de0cd20ac77be1d3f2c', TRUE);

GO

INSERT INTO users (ID, USERNAME,PASSWORD, ENABLED)
VALUES (3, 'admin', 'd033e22ae348aeb5660fc2140aec35850c4da997', TRUE);

GO

INSERT INTO user_roles (ID, USER_ID,AUTHORITY)
VALUES (1, 1, 'ROLE_USER');

GO

INSERT INTO user_roles (ID, USER_ID,AUTHORITY)
VALUES (2, 2, 'ROLE_USER');

GO

INSERT INTO user_roles (ID, USER_ID,AUTHORITY)
VALUES (3, 3, 'ROLE_USER');

GO

INSERT INTO articles (ID, UNIQUE_NAME, TITLE, CREATION_DATE, AUTHOR, DEMO_NAME, EXAMPLE_FILE_NAME, SOURCE_FILE_NAME, STATUS)
VALUES (1, 'hello-world-servlets', 'Hello World Servlets', '2012-01-04 12:00:00', 'Chris', 'HelloWorldServlets', 'HelloWorldServlets.war', 'HelloWorldServlets.zip', 'ACTIVE');

GO

INSERT INTO articles (ID, UNIQUE_NAME, TITLE, CREATION_DATE, AUTHOR, STATUS)
VALUES (2, 'build-war-with-maven-windows', 'Build war file with Maven (Windows)', '2012-10-22 12:00:00', 'Chris', 'ACTIVE');

GO

INSERT INTO articles (ID, UNIQUE_NAME, TITLE, CREATION_DATE, AUTHOR, STATUS, DEMO_NAME, EXAMPLE_FILE_NAME)
VALUES (3, 'deploy-run-application-on-tomcat-7', 'Deploy and run application on Tomcat 7', '2012-10-02 12:00:00', 'Chris', 'ACTIVE', 'hello', 'hello.war');

GO

INSERT INTO article_tags (ID, UNIQUE_NAME, NAME)
VALUES (1, 'java', 'Java');

GO

INSERT INTO article_tags (ID, UNIQUE_NAME, NAME)
VALUES (2, 'servlets', 'Servlets');

GO

INSERT INTO article_tags (ID, UNIQUE_NAME, NAME)
VALUES (3, 'html', 'Html');

GO

INSERT INTO article_tags (ID, UNIQUE_NAME, NAME)
VALUES (4, 'jsp', 'Jsp');

GO

INSERT INTO article_tags (ID, UNIQUE_NAME, NAME)
VALUES (5, 'css', 'Css');

GO

INSERT INTO article_tags (ID, UNIQUE_NAME, NAME)
VALUES (6, 'java_script', 'Java Script');

GO

INSERT INTO article_tags (ID, UNIQUE_NAME, NAME)
VALUES (7, 'maven', 'Maven');

GO

INSERT INTO article_tags (ID, UNIQUE_NAME, NAME)
VALUES (8, 'tomcat', 'Tomcat');

GO

INSERT INTO article_tags (ID, UNIQUE_NAME, NAME)
VALUES (9, 'windows', 'Windows');

GO

INSERT INTO join_article_and_tag (ARTICLE_ID, ARTICLE_TAG_ID)
VALUES (1, 1);

GO

INSERT INTO join_article_and_tag (ARTICLE_ID, ARTICLE_TAG_ID)
VALUES (1, 2);

GO

INSERT INTO join_article_and_tag (ARTICLE_ID, ARTICLE_TAG_ID)
VALUES (1, 3);

GO

INSERT INTO join_article_and_tag (ARTICLE_ID, ARTICLE_TAG_ID)
VALUES (1, 4);

GO

INSERT INTO join_article_and_tag (ARTICLE_ID, ARTICLE_TAG_ID)
VALUES (1, 5);

GO

INSERT INTO join_article_and_tag (ARTICLE_ID, ARTICLE_TAG_ID)
VALUES (1, 6);

GO

INSERT INTO join_article_and_tag (ARTICLE_ID, ARTICLE_TAG_ID)
VALUES (1, 7);

GO

INSERT INTO join_article_and_tag (ARTICLE_ID, ARTICLE_TAG_ID)
VALUES (1, 8);

GO

INSERT INTO join_article_and_tag (ARTICLE_ID, ARTICLE_TAG_ID)
VALUES (2, 1);

GO

INSERT INTO join_article_and_tag (ARTICLE_ID, ARTICLE_TAG_ID)
VALUES (2, 7);

GO

INSERT INTO join_article_and_tag (ARTICLE_ID, ARTICLE_TAG_ID)
VALUES (2, 9);

GO

INSERT INTO join_article_and_tag (ARTICLE_ID, ARTICLE_TAG_ID)
VALUES (3, 1);

GO

INSERT INTO join_article_and_tag (ARTICLE_ID, ARTICLE_TAG_ID)
VALUES (3, 8);

GO