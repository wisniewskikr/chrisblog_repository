STEPS CHRIS`S BLOG DEPLOYMENT

1. Handle folder with demo`s sources
- create folder with demo`s sources in location <tomcat_home>/webapps (default: chrisblog_sources)
- copy all demo`s sources to folder with demo`s sources
- update paths to folders with demo`s sources in property files in location <project_home>/profiles


2. Handle folder with demo`s examples
- create folder with demo`s examles in location <tomcat_home>/webapps (default: chrisblog_examples)
- copy all demo`s examples to folder with demo`s examples
- update paths to folders with demo`s examples in property files in location <project_home>/profiles


3. Update profiles
- update profiles in properties files in location <project_home>/profiles
- update profiles in pom.xml


4. Build project
Build project using maven profiles:

- with server location:
* loc: local/development machine
* rem: remote/jenkins machine
* prod: production machine

- with tasks:
* deploy: deploying project on server (local, remote or production) 
* unit: run unit tests
* intg: run integration tests

For instance command for deploying application on local machine and running
unit and integration tests:
mvn clean install -Ploc,deploy,unit,intg
