package com.smarttestinglab.lesson8;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
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
    private static final String APPIUM_SERVER_URL = "http://127.0.0.1:4723";

    public static void main(String[] args) throws MalformedURLException {
        XCUITestOptions options = new XCUITestOptions()
                .setPlatformName("iOS")
                .setPlatformVersion("18.5")
                .setAutomationName("XCUITest")
                .setDeviceName("Android Emulator")
                .setApp("/Users/doaitran/Documents/Personal/Coding/premium-appium-python-course/build/saucelab/my_demo_app.app")
                .setNoReset(false);
        IOSDriver driver;
        driver = new IOSDriver(new URL(APPIUM_SERVER_URL), options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        System.out.println("Test setup completed successfully");
        WebElement appLogoAndName = wait.until(ExpectedConditions.visibilityOfElementLocated(
                AppiumBy.accessibilityId("AppLogo Icons")));
        Assert.assertTrue(appLogoAndName.isDisplayed(), "App Logo should be visible");
        System.out.println("App launched successfully - App Logo is visible");
        System.out.println("Closing driver...");
        driver.quit();
        System.out.println("Driver closed successfully");
    }
}
