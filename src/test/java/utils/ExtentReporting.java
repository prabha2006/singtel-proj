package utils;

import com.aventstack.extentreports.ExtentReports;

import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;

public class ExtentReporting {
    /*
    public static ExtentHtmlReporter htmlReporter;
    public static ExtentReports extent;

    public static ExtentReports getExtentInstance() {
        if (extent == null) {
            htmlReporter = new ExtentHtmlReporter(Constants.ReportBasePath);
            extent = new ExtentReports();
            extent.attachReporter(htmlReporter);
            htmlReporter.loadConfig("./src/main/resources/html-config.xml");
            extent.setSystemInfo("Host Name", "https://todomvc.com/examples/vue/");
            extent.setSystemInfo("Environment", "QA");
            extent.setSystemInfo("User Name", "VIKRAM");


            htmlReporter.config().setDocumentTitle("AutomationTesting Report");
            htmlReporter.config().setReportName("todos");
            htmlReporter.config().setTheme(Theme.DARK);
        }
        return extent;
    }*/

    public static String takeSnapShot(WebDriver webdriver, String filename) throws Exception {
        String DestFilePath = utils.Constants.SCREENSPATH + filename;
        TakesScreenshot scrShot = ((TakesScreenshot) webdriver);
        File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
        File DestFile = new File(DestFilePath);
        FileUtils.copyFile(SrcFile, DestFile);
        return DestFilePath;
    }
}

