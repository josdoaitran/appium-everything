package com.smarttestinglab.lesson8;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class FirstTestIosAppiumTestNG {
    private IOSDriver driver;
    public static final String APP_DIRECTORY = "build/my_demo_app.app";

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

    @Test(priority = 1)
    public void testAppProductList() {
        System.out.println("Test App Product List");
        WebElement appProductList = driver.findElement(AppiumBy.accessibilityId("title"));
        Assert.assertTrue(appProductList.isDisplayed());
        System.out.println("App Product List is visible");
    }

    @Test(priority = 2)
    public void testAppMenu(){
        System.out.println("Test App Menu");
        System.out.println("Touch on App Menu");
        WebElement appMenuIcon = driver.findElement(AppiumBy.accessibilityId("More-tab-item"));
        appMenuIcon.click();
        System.out.println("Verify App Menu");
        WebElement appMenuLogin = driver.findElement(AppiumBy.accessibilityId("LogOut-menu-item"));
        Assert.assertTrue(appMenuLogin.isDisplayed());
        System.out.println("App Menu Login is visible");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
        System.out.println("Driver closed successfully");
    }
}
