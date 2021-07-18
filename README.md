# singtel-proj
FrameWork Details:
1. BDD framework - Cucumber, TestNg, Selenium with Java, Page Object Model
2. Logging framework: log4j
3. Reporting tool:Extent spark html report
4. ITest testng listener

Steps to run the prj:
1. Run SuiteXML-TODO - testng suite xml
2. Look for extent reports  - /test-output/SparkReport/dashboard.html
3. Setting for the report can be changed at html-config.xml
4. Feature file -src/test/java/features/todo.feature
5. Runner file - src/test/java/runner/TestRunner
6. Step def - src/test/java/steps/Stepdefs
7. Pages [POM]-src/test/java/pages
8. Other files are under src/test/java/utils
9. drivers - src/main/resources/drivers [only chromedriver.exe is used to this demo purpose]
10. Other properties files - src/main/resources



