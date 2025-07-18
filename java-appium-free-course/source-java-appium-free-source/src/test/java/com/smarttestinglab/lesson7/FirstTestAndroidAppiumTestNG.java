package com.smarttestinglab.lesson7;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class FirstTestAndroidAppiumTestNG {
    private AndroidDriver driver;
    private static final String APP_PACKAGE = "com.saucelabs.mydemoapp.android";
    private static final String APP_ACTIVITY = "com.saucelabs.mydemoapp.android.view.activities.MainActivity";
    private static final String APPIUM_SERVER = "http://127.0.0.1:4723";

    @BeforeMethod
    public void setup() throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName("Android")
                .setDeviceName("Android Emulator")
                .setAutomationName("UiAutomator2")
                .setApp("/Users/doaitran/Documents/Personal/Coding/appium-everything/java-appium-free-course/source-java-appium-free-source/apps/mda-2.2.0-25.apk")
                .setNoReset(false)
                .setAutoGrantPermissions(true)
                .setAppPackage(APP_PACKAGE)
                .setAppWaitActivity(APP_ACTIVITY);
        driver = new AndroidDriver(new URL(APPIUM_SERVER), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        System.out.println("Test Setup completed successfully");
        WebElement appLogoName = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("App logo and name")));
        Assert.assertTrue(appLogoName.isDisplayed());
        System.out.println("App launched successfully - App Logo is visible");
    }

    @Test()
    public void testAppProductList() {
        System.out.println("Test App Product List");
        WebElement appProductList = driver.findElement(AppiumBy.id("com.saucelabs.mydemoapp.android:id/productTV"));
        Assert.assertTrue(appProductList.isDisplayed());
        System.out.println("App Product List is visible");
    }

    @Test()
    public void testAppMenu(){
        System.out.println("Test App Menu");
        System.out.println("Touch on App Menu");
        WebElement appMenu = driver.findElement(AppiumBy.accessibilityId("View menu"));
        appMenu.click();
        System.out.println("Verify App Menu");
        WebElement appMenuLogin = driver.findElement(AppiumBy.accessibilityId("Login Menu Item"));
        Assert.assertTrue(appMenuLogin.isDisplayed());
        System.out.println("App Menu Login is visible");
    }


    @AfterMethod
    public void tearDown() {
        driver.quit();
        System.out.println("Driver closed successfully");
    }

}
