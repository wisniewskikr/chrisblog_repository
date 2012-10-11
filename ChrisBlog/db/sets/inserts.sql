INSERT INTO users (ID, USERNAME,PASSWORD, ENABLED)
VALUES (1, 'chris', 'e50b3c5d55c6565bc0dcfbedae036e1b0dcb1f92', TRUE);

GO

INSERT INTO user_roles (ID, USER_ID,AUTHORITY)
VALUES (1, 1, 'ROLE_USER');

GO

INSERT INTO articles (ID, UNIQUE_NAME, TITLE, PAGES_COUNT, CREATION_DATE, AUTHOR, DEMO_NAME, EXAMPLE_FILE_NAME, SOURCE_FILE_NAME, STATUS)
VALUES (1, 'hello_world_servlets', 'Hello World Servlets', 4, '2012-01-04 12:00:00', 'Chris', 'HelloWorldServlets', 'HelloWorldServlets.war', 'HelloWorldServlets.zip', 'ACTIVE');

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