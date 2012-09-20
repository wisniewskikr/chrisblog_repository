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


5. Build project
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

test