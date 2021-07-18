package steps;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import freemarker.log.Logger;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.HomePage;
import utils.Constants;
import utils.DriverSetUp;
import utils.ExtentReporting;
import utils.Logs;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Stepdefs {
    HomePage homePage = new HomePage();
    List<String> todoList = new ArrayList<>();
    int toDoFullSize, toDoToCrossSize, toDoAfterCrossedSize;

    WebDriver driver;

    @Before
    public static void startTest() throws ClassNotFoundException {
        BasicConfigurator.configure();
        freemarker.log.Logger.selectLoggerLibrary(Logger.LIBRARY_NONE);
        Logs.info("Application under test:" + Constants.AppURL);
    }

    @Given("^user launch the chrome browser$")
    public void launchBrowser(List<String> browserType) throws Exception {
        driver = DriverSetUp.setUpDriver(browserType.get(0));
        driver.get(Constants.AppURL);
        driver.manage().window().maximize();
    }

    @Then("verify page launched successfully")
    public void verifyTitle() throws Exception {
        Assert.assertTrue(driver.getTitle().equalsIgnoreCase("Vue.js • TodoMVC"));
        //test.log(Status.PASS, "Title matcher");
    }

    @Given("^User enters todo list$")
    public void userEntersTodoList(List<String> listOfTodo) throws InterruptedException {
        toDoFullSize = listOfTodo.size();
        todoList = listOfTodo;
        for (String todo : listOfTodo) {
            homePage.createToDoLists(driver, todo);
        }
        Thread.sleep(1000);
    }

    @Then("^verify the tasks count on the webpage$")
    public void checkToDoCount() {
        Assert.assertTrue(Integer.parseInt(homePage.getToDOCount(driver)) == toDoFullSize);
    }

    @And("verify the tasks name on the webpage")
    public void checkToDoText() {
        List<String> todolistFromPage = homePage.getTodoText(driver);
        if (!todolistFromPage.isEmpty())
            Assert.assertTrue(todolistFromPage.equals(todoList));
        else {
            Assert.fail();
            System.out.println("Not able to find any Todo element!!!");
        }
    }

    @And("user want to delete tasks")
    public void userWantToDeleteTasks(List<String> lists) throws InterruptedException {
        for (String tasks : lists)
            homePage.clickDestroyButton(driver, tasks);
    }

    @Then("verify the task is deleted")
    public void verifyTheTaskIsDeleted(List<String> lists) throws InterruptedException {
        Boolean flag;
        for (String tasks : lists) {
            flag = homePage.existsElementToDoByLabel(driver, tasks);
            Assert.assertTrue(!flag);
        }
    }

    @When("user edits the task")
    public void editTask(List<Map<String, String>> lists) throws InterruptedException {
        List<String> editTask = new ArrayList<>();
        int i = 0;
        for (Map<String, String> map : lists) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                //System.out.println("Key = " + entry.getKey() +", Value = " + entry.getValue());
                editTask.add(i, entry.getValue());
                i++;
            }
        }
        System.out.println(editTask);
        for (int j = 0; j < editTask.size(); j += 2) {
            homePage.editTodoByLabel(driver, editTask.get(j), editTask.get(j + 1));
        }
    }

    @Then("verify user is not displayed with old tasks")
    public void verifyUserIsNotDisplayedWithOldTasks(List<String> lists) throws InterruptedException {
        for (String tasks : lists) {
            Assert.assertFalse(homePage.existsElementToDoByLabel(driver, tasks));
        }
    }

    @And("verify user is displayed with edited tasks")
    public void verifyUserIsDisplayedWithEditedTasks(List<String> lists) throws InterruptedException {
        for (String tasks : lists) {
            Assert.assertTrue(homePage.existsElementToDoByLabel(driver, tasks));
        }
    }

    @When("user check the tasks")
    public void checkTodo(List<String> lists) throws InterruptedException {
        toDoFullSize = Integer.parseInt(homePage.getToDOCount(driver));
        toDoToCrossSize = lists.size();
        for (String tasks : lists)
            homePage.checkTodoByName(driver, tasks);
    }

    @Then("task count should decrease")
    public void taskCountShouldDecrease() {
        toDoAfterCrossedSize = Integer.parseInt(homePage.getToDOCount(driver));
        System.out.println("Todo total count:" + toDoFullSize + " | Todo to be checked count: " + toDoToCrossSize + " | Actual todo count after checked: " + toDoAfterCrossedSize);
        Assert.assertTrue(toDoFullSize - toDoToCrossSize == toDoAfterCrossedSize);
    }

    @And("proper button should appear")
    public void properButtonShouldAppear() throws InterruptedException {
        Assert.assertTrue(homePage.isElementExistsClearCompleteButton(driver));
    }

    @And("user uncheck the tasks checked previously")
    public void UnChkTodo(List<String> lists) throws InterruptedException {
        checkTodo(lists);
    }

    @Then("task count should increase after uncheck")
    public void taskCountShouldIncreaseAfterUncheck() {
        toDoAfterCrossedSize = Integer.parseInt(homePage.getToDOCount(driver));
        System.out.println("Todo total count:" + toDoFullSize + " | Todo to uncheck count: " + toDoToCrossSize + " | Actual todo count after unchecked: " + toDoAfterCrossedSize);
        Assert.assertTrue(toDoFullSize + toDoToCrossSize == toDoAfterCrossedSize);
    }

    @Then("button should disappear")
    public void buttonShouldDisappear() throws InterruptedException {

        Assert.assertFalse(homePage.isElementExistsClearCompleteButton(driver));
    }

    @And("completed tasks should not be displayed")
    public void completedTasksShouldNotBeDisplayed(List<String> lists) throws InterruptedException {
        verifyUserIsNotDisplayedWithOldTasks(lists);
    }


    @And("user remove todo from the list")
    public void userRemoveTodoFromTheList() throws InterruptedException {
        homePage.clickClearCompleteButton(driver);
    }

    public void userClicksOnLink(WebDriver driver, String linkType) throws InterruptedException {
        switch (linkType) {
            case "All":
                homePage.clickOnAllLink(driver);
                break;
            case "Active":
                homePage.clickOnActiveLink(driver);
                break;
            case "Completed":
                homePage.clickOnCompletedLink(driver);
                break;
        }
    }

    @Then("user should see All todos")
    public void userShouldSeeAllTodos(List<String> lists) throws InterruptedException {
        verifyUserIsDisplayedWithEditedTasks(lists);
        System.out.println("All todos");
    }

    @Then("user should see only Active todos")
    public void userShouldSeeOnlyActiveTodos(List<String> lists) throws InterruptedException {
        verifyUserIsDisplayedWithEditedTasks(lists);
        System.out.println("Active todos");
    }

    @Then("user should see only  Completed todos")
    public void userShouldSeeOnlyCompletedTodos(List<String> lists) throws InterruptedException {
        verifyUserIsDisplayedWithEditedTasks(lists);
        System.out.println("completed todos");
    }

    @When("user clicks on Active")
    public void userClicksOnActive() throws InterruptedException {
        userClicksOnLink(driver, "Active");
    }

    @And("user clicks on All")
    public void userClickOnAll() throws InterruptedException {
        userClicksOnLink(driver, "All");
    }

    @When("user clicks on Completed")
    public void userClicksOnCompleted() throws InterruptedException {
        userClicksOnLink(driver, "Completed");
    }

    @Then("verify the heading")
    public void verifyTheHeading() {
        Assert.assertTrue(homePage.getHeading(driver).equalsIgnoreCase("todos"));
    }

    @And("verify the default text in textBox")
    public void verifyTheDefaultTextInTextBox() {
        Assert.assertTrue(homePage.getDefaultTextBox(driver).equalsIgnoreCase("What needs to be done?"));
    }

    @After
    public void tearDown() {
        //ExtentReporting.extent.flush();
        driver.close();
    }
}
