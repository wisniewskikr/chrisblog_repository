DROP TABLE IF EXISTS articles;

GO

CREATE TABLE articles (
  ID BIGINT(20) NOT NULL auto_increment,
  UNIQUE_NAME VARCHAR(100) NOT NULL,
  TITLE VARCHAR(100) NOT NULL,
  PAGES_COUNT INT NOT NULL,
  CREATION_DATE TIMESTAMP NOT NULL,
  AUTHOR VARCHAR(50) NOT NULL,
  DEMO_NAME VARCHAR(100),
  EXAMPLE_FILE_NAME VARCHAR(100),
  SOURCE_FILE_NAME VARCHAR(100),
  STATUS VARCHAR(20) NOT NULL,
  PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

GO