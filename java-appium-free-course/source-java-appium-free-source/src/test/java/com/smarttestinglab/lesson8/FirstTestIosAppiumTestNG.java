package com.smarttestinglab.lesson8;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
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

public class FirstTestIosAppiumTestNG {
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
    public void setup() throws MalformedURLException {
        XCUITestOptions options = new XCUITestOptions()
                .setPlatformName("iOS")
                .setPlatformVersion("18.5")
                .setDeviceName("iPhone 16 Pro")
                .setAutomationName("XCUITest")
                .setApp(getAppDirectory())
                .setNoReset(false);
        driver = new IOSDriver(new URL("http://127.0.0.1:4723"), options);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        System.out.println("Test Setup completed successfully");
        WebElement appLogoName = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("AppTitle Icons")));
        Assert.assertTrue(appLogoName.isDisplayed());
    }


    @Test(priority = 1, description = "Verify app launches successfully")
    public void testAppLaunch() {
        System.out.println("Starting testAppLaunch...");
        WebElement appLogoAndName = wait.until(ExpectedConditions.visibilityOfElementLocated(
                AppiumBy.accessibilityId("AppLogo Icons")));
        Assert.assertTrue(appLogoAndName.isDisplayed(), "App Logo should be visible");
        System.out.println("App launched successfully - App Logo is visible");
    }

    @Test(priority = 2, description = "Verify Menu app")
    public void testAppMenu() {
        System.out.println("Starting testAppMenu...");
        WebElement menuIcon = driver.findElement(AppiumBy.accessibilityId("More-tab-item"));
        menuIcon.click();
        WebElement loginInMenu = driver.findElement(AppiumBy.accessibilityId("LogOut-menu-item"));
        Assert.assertTrue(loginInMenu.isDisplayed());

    }
}
