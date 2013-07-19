USER MANUAL
===========


I. DATABASE
For this project is used database MySql. Configuration steps:


1. Create database
You have to crate database in MySql for this project. By default it is:
- database name			: chrisblog_db


2. Update pom.xml file
You have to add database location and credentials in pom.xml file.
You have to do it in profile "loc". By default it is:
- db.url				: jdbc:mysql://localhost:3306/chrisblog_db
- db.driver				: com.mysql.jdbc.Driver
- db.username			: root
- db.password			: P@ssw0rd 



II. SERVER
For this project is used server Tomcat 7. Configuration steps:


1. Add user to server
In location <tomcat_home>/conf/tomcat-users.xml create user with role 'manager-gui' and 'manager-script'.
For example:
<role rolename="manager-gui"/>
<role rolename="manager-script"/>
<user password="admin" roles="manager-gui, manager-script" username="admin"/>


2. Update pom.xml file
You have to add server information to pom.xml file.
You have to do it in profile "loc". By default it is:
- server.url			: http://localhost:8080/manager/text
- server.username		: admin
- server.password		: admin



III. PROJECT
To fill database with project data and start project you have to do following steps:


1. Run server


2. Run maven command
To fill database with data, build war file and deploy it on server 
run following maven command:
mvn clean install -Ploc,deploy


3. Test solution
For testing solution type in browser:
http://localhost:8080/ChrisBlog/



-----



STEPS SERVER`S PREPARATION (TOMCAT 7)

1. Create Tomcat`s user with permission to remote deploying
- in location <tomcat_home>/conf/tomcat-users.xml create user with role 'manager-gui'. For instance: 
<user username='admin' password='admin' roles='tomcat,role1,admin,manager,manager-gui,admin-gui,manager-script'/>



-----



STEPS CHRIS`S BLOG DEPLOYMENT

1. Handle folder with demo`s sources
- create folder with demo`s sources in location <tomcat_home>/webapps (default: chrisblog_sources)
- copy all demo`s sources to folder with demo`s sources


2. Handle folder with demo`s examples
- create folder with demo`s examles in location <tomcat_home>/webapps (default: chrisblog_examples)
- copy all demo`s examples to folder with demo`s examples


3. Deploy example applications


4. Update profiles
- update profiles in pom.xml


5. Create dababases
By default you have to create in MySql two databases:
- chrisblog_db
- chrisblog_db_test


6. Build project
Build project using maven profiles:

- with server location:
* loc: local/development machine
* rem: remote/jenkins machine
* prod: production machine

- with tasks:
* deploy: deploying project on server (local, remote or production) 
* unit: run unit tests
* intg: run integration tests

For instance: command for deploying application on local machine and running
unit and integration tests:
mvn clean install -Ploc,deploy,unit,intg