# singtel-proj
FrameWork Details:
1. BDD framework - Cucumber, TestNg, Selenium with Java, Page Object Model
2. Logging framework: log4j
3. Reporting tool:Extent spark html report
4. ITest testng listener

Steps to run the prj:
1. Click on  Add configuration/+ (add new configuration on the left corner)/TestNg
  a. Enter SingTel-todos
  b. Under Configuration/Set Test Kind  as Suite/Choose Suite "testng.xml" from src folder
  c. Make proper JRE is chosen
  d. Remove -ea from VM options
  e. Apply/OK
2. Look for TestRunner under src\test\java\runner folder structure to change the tags accordingly to run 
3. Look for extent reports  - /test-output/SparkReport/dashboard.html
4. Setting for the report can be changed at html-config.xml
5. Feature file -src/test/java/features/todo.feature
6. Runner file - src/test/java/runner/TestRunner
7. Step def - src/test/java/steps/Stepdefs
8. Pages [POM]-src/test/java/pages
9. Other files are under src/test/java/utils
10. drivers - src/main/resources/drivers [only chromedriver.exe is used to this demo purpose]
11. Other properties files - src/main/resources



