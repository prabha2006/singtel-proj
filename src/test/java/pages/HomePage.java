package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;

public class HomePage {

    WebDriver driver;
    Actions act;

    By todosHeader = By.xpath("//h1");
    By newTodoTextBox = By.className("new-todo");
    By toDoLists = By.xpath("//li[@class='todo']/div/label");
    By allLink = By.xpath("//ul[@class='filters']/li[1]");
    By activeLink = By.xpath("//ul[@class='filters']/li[2]");
    By completedLink = By.xpath("//ul[@class='filters']/li[3]");
    By todoCount = By.xpath("//span[@class='todo-count']/strong");
    By clearCompleteButton = By.className("clear-completed");
    String destroyButton = "//label[text()='%s']/following-sibling::button";
    String toDoLabel = "//label[text()='%s']";
    String todoCheckBox = "//label[text()='%s']/preceding-sibling::input";

    public void clickOnAllLink(WebDriver driver) {
        driver.findElement(allLink).click();
    }

    public void clickOnActiveLink(WebDriver driver) {
        driver.findElement(activeLink).click();
    }

    public void clickOnCompletedLink(WebDriver driver) {
        driver.findElement(completedLink).click();
    }

    public String getHeading(WebDriver driver) {
        String header = driver.findElement(todosHeader).getText();
        return header;
    }

    public void createToDoLists(WebDriver driver, String todo) {
        driver.findElement(newTodoTextBox).sendKeys(todo);
        act = new Actions(driver);
        act.sendKeys(Keys.ENTER).build().perform();
    }

    public String getToDOCount(WebDriver driver) {
        return driver.findElement(todoCount).getText();
    }

    public List<String> getTodoText(WebDriver driver) {
        List<String> listTexts = new ArrayList<>();
        List<WebElement> toDoElements = driver.findElements(toDoLists);
        int i = 0;
        for (WebElement ele : toDoElements) {
            System.out.println("Elements: " + ele.getText());
            listTexts.add(i, ele.getText());
            i++;
        }
        return listTexts;
    }

    public void clickDestroyButton(WebDriver driver, String textToReplace) throws InterruptedException {
        act.moveToElement(driver.findElement(By.xpath(String.format(toDoLabel, textToReplace)))).
                moveToElement(driver.findElement(By.xpath(String.format(destroyButton, textToReplace)))).
                click().build().perform();
        Thread.sleep(1000);
    }

    public boolean existsElementToDoByLabel(WebDriver driver, String textToReplace) throws InterruptedException {
        return existsElement(driver, By.xpath(String.format(toDoLabel, textToReplace)));
    }

    public boolean existsElement(WebDriver driver, By by) {
        if (driver.findElements(by).size() == 0) {
            return false;
        } else {
            return true;
        }
    }

    public void editTodoByLabel(WebDriver driver, String textToReplace, String newTodo) throws InterruptedException {
        act.moveToElement(driver.findElement(By.xpath(String.format(toDoLabel, textToReplace))))
                .doubleClick()
                .keyDown(Keys.CONTROL)
                .sendKeys("a")
                .keyUp(Keys.CONTROL)
                .sendKeys(Keys.BACK_SPACE)
                .sendKeys(newTodo)
                .sendKeys(Keys.ENTER)
                .build().perform();
        Thread.sleep(1000);
    }

    public void checkTodoByName(WebDriver driver, String textToReplace) throws InterruptedException {
        driver.findElement(By.xpath(String.format(todoCheckBox, textToReplace))).click();
        Thread.sleep(2000);
    }

    public boolean isElementExistsClearCompleteButton(WebDriver driver) {
        return existsElement(driver, clearCompleteButton);
    }

    public void clickClearCompleteButton(WebDriver driver) {
        driver.findElement(clearCompleteButton).click();
    }

    public String getDefaultTextBox(WebDriver driver){
        return driver.findElement(newTodoTextBox).getAttribute("placeholder");
    }

}
