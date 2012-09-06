STEPS CHRIS`S BLOG DEPLOYMENT

1. In Application Server create folder with sources

2. In Application Server create folder with examples

3. In Application Server deploy demo projects

4. Create project profiles
Create profiles in pom.xml
In Eclipse update Java-Build Path:
- every java (main and test): included-all, excluded-none
- every resources (main and test): included-all, excluded-**
- checked checkbox: "Allow output folders for source folders"

5. Create project properties
Create properties for every profile. For instance:
- project.properties_dev
- project.properties_test
- project.properties_prod

6. Build ChrisBlog project
Use:
- development mode: mvn clean install or mvn clean install -Pdev
- test mode: mvn clean install -Ptest
- production mode: mvn clean install -Pprod

5. Deploy ChrisBlog project

Aaa

Bbb

Ccc

Ddd

Eee