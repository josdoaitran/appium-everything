package com.smarttestinglab.lesson8;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class FirstTestIosAppium {

    // App details for SauceLabs MyDemo Android App
    private static final String APP_PACKAGE = "com.saucelabs.mydemoapp.android";
    private static final String APP_ACTIVITY = "com.saucelabs.mydemoapp.android.view.activities.MainActivity";
    private static final String APPIUM_SERVER_URL = "http://127.0.0.1:4723";

    public static void main(String[] args) throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName("Android")
                .setPlatformVersion("11")
                .setAutomationName("UiAutomator2")
                .setDeviceName("Android Emulator")
                .setApp("/Users/doaitran/Documents/Personal/Coding/appium-everything/java-appium-free-course/source-java-appium-free-source/apps/mda-2.2.0-25.apk")
                .setNoReset(false)
                .setAutoGrantPermissions(true)
                .setAppPackage(APP_PACKAGE)
                .setAppWaitActivity(APP_ACTIVITY);
        AndroidDriver driver;
        driver = new AndroidDriver(new URL(APPIUM_SERVER_URL), options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        System.out.println("Test setup completed successfully");
        WebElement appLogoAndName = wait.until(ExpectedConditions.visibilityOfElementLocated(
                AppiumBy.accessibilityId("App logo and name")));
        Assert.assertTrue(appLogoAndName.isDisplayed(), "App Logo should be visible");
        System.out.println("App launched successfully - App Logo is visible");
        System.out.println("Closing driver...");
        driver.quit();
        System.out.println("Driver closed successfully");
    }
}
