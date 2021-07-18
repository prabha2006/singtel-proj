package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class DriverSetUp {

    private static WebDriver driver;

    public static WebDriver setUpDriver(String browserType) {
        String browser = browserType.toUpperCase();

        switch (browser) {
            case "CHROME":
                System.setProperty("webdriver.chrome.driver", Constants.driverBasePath + "chromedriver.exe");
                driver = new ChromeDriver();
                break;
            case "IE":
                System.setProperty("webdriver.ie.driver", Constants.driverBasePath + "IEDriverServer.exe");
                driver = new InternetExplorerDriver();
                break;
        }
        return driver;
    }
}
