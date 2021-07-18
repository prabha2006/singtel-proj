package utils;

import org.testng.ITestListener;
import org.testng.ITestContext;
import org.testng.ITestResult;
import steps.Stepdefs;

public class ListenerClass implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ITestListener.super.onTestSkipped(result);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("onStart: " + context.getClass());
        // Stepdefs.test = Stepdefs.report.createTest(context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {

    }

    @Override
    public void onTestStart(ITestResult result) {

    }
}
