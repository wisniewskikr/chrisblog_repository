DROP TABLE IF EXISTS article_tags;

GO

CREATE TABLE article_tags (
  ID INT(10) UNSIGNED NOT NULL,
  UNIQUE_NAME VARCHAR(100) NOT NULL,
  NAME VARCHAR(100) NOT NULL,
  PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

GO