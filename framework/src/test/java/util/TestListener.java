package util;

import driver.DriverSingleton;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;


public class TestListener implements ITestListener {
    private static final Logger LOGGER = LogManager.getRootLogger();

    public void onTestStart(ITestResult iTestResult) {
        LOGGER.log(Level.INFO, "Test is started");
    }

    public void onTestSuccess(ITestResult iTestResult) {
        LOGGER.log(Level.INFO, "Test is passed");
    }

    public void onTestFailure(ITestResult iTestResult) {
        saveScreenshot();
        LOGGER.log(Level.ERROR, "Test is failed");
    }

    public void onTestSkipped(ITestResult iTestResult) {}

    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {}

    public void onStart(ITestContext iTestContext) {}

    public void onFinish(ITestContext iTestContext) {}

    private void saveScreenshot(){
        File screenCapture = ((TakesScreenshot) DriverSingleton
                .getDriver())
                .getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenCapture, new File(
                    ".//target/screenshots/"
                    + getCurrentTimeAsString() +
                    ".png"));
        } catch (IOException e) {
            LOGGER.log(Level.ERROR, "Failed to save screenshot: " + e.getLocalizedMessage());
        }
    }

    private String getCurrentTimeAsString(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern( "uuuu-MM-dd_HH-mm-ss" );
        return ZonedDateTime.now().format(formatter);
    }
}
