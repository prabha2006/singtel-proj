# singtel-proj
FrameWork Details:
1. BDD framework - Cucumber, TestNg, Selenium with Java, Page Object Model
2. Logging framework: log4j
3. Reporting tool:Extent spark html report
4. ITest testng listener

Steps to run the prj:
1. Click on  Add configuration/+ (add new configuration on the left corner)/TestNg
2. Enter SingTel-todos
3. Under Configuration/Set Test Kind  as Suite/Choose Suite "testng.xml" from src folder
4. Make proper JRE is chosen
5. Remove -ea from VM option
6. Apply/OK
7. ----------
9. Look for TestRunner under src\test\java\runner folder structure to change the tags accordingly to run 
10. Look for extent reports  - /test-output/SparkReport/dashboard.html
11. Setting for the report can be changed at html-config.xml
12. Feature file -src/test/java/features/todo.feature
13. Runner file - src/test/java/runner/TestRunner
14. Step def - src/test/java/steps/Stepdefs
15. Pages [POM]-src/test/java/pages
16. Other files are under src/test/java/utils
17. drivers - src/main/resources/drivers [only chromedriver.exe is used to this demo purpose]
18. Other properties files - src/main/resources



