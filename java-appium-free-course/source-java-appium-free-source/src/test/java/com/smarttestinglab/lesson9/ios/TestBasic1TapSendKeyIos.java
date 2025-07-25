package com.smarttestinglab.lesson9.ios;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class TestBasic1TapSendKeyIos {
    private IOSDriver driver;
    private WebDriverWait wait;

    // App details for SauceLabs MyDemo Android App
    private static final String APP_PACKAGE = "com.saucelabs.mydemoapp.ios";
    private static final String APPIUM_SERVER_URL = "http://127.0.0.1:4723";
    public static final String APP_DIRECTORY = "app/my_demo_app.app";

    public static String getAppDirectory() {
        File file = new File(APP_DIRECTORY);
        return file.getAbsolutePath();
    }
    @BeforeMethod
    public void setUp() throws MalformedURLException {
        // Configure UiAutomator2 options for iOS
        UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName("iOS")
                .setPlatformVersion("18.5")
                .setAutomationName("XCUITest")
                .setDeviceName("iPhone 14")
                .setApp(getAppDirectory())
                .setNoReset(false)
                .setAutoGrantPermissions(true);
        // Initialize IOSDriver
        driver = new IOSDriver(new URL(APPIUM_SERVER_URL), options);
        // Initialize WebDriverWait
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        System.out.println("Test setup completed successfully");
    }


    @Test
    public void testBasicTapSendKey () {
        WebElement menuIcon = driver.findElement(AppiumBy.id(APP_PACKAGE + ":id/menuIV"));
        menuIcon.click();
        WebElement loginOptionMenu = driver.findElement(AppiumBy.accessibilityId("Login Menu Item"));
        loginOptionMenu.click();
        WebElement username = driver.findElement(AppiumBy.id(APP_PACKAGE + ":id/nameET"));
        username.sendKeys("bod@example.com");
        WebElement password = driver.findElement(AppiumBy.id(APP_PACKAGE + ":id/passwordET"));
        password.sendKeys("10203040");
        WebElement loginButton = driver.findElement(AppiumBy.id(APP_PACKAGE + ":id/loginBtn"));
        loginButton.click();
    }
}
