package com.smarttestinglab.lesson9.ios;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
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
import java.util.Arrays;

public class TestBasic4GetAttributeElementIos {
    private IOSDriver driver;
    private WebDriverWait wait;

    // App details for SauceLabs MyDemo Android App
    public static final String APP_DIRECTORY = "apps/my_demo_app.app";

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
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        System.out.println("Test Setup completed successfully");
        WebElement appLogoName = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("AppTitle Icons")));
        Assert.assertTrue(appLogoName.isDisplayed());
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testGetElementAttributes() {
        try {
            Thread.sleep(3000);
            // Find menu button
            WebElement menuButton = driver.findElement(AppiumBy.accessibilityId("More-tab-item"));
            // Get various attributes
            // Only Android // IOS does not support "class" "text" attribute
//            String elementText = menuButton.getAttribute("text");
//            String elementClass = menuButton.getAttribute("class");
            String elementEnabled = menuButton.getAttribute("enabled");
            boolean elementDisplayed = menuButton.isDisplayed();
            Dimension elementSize = menuButton.getSize();
            Point elementLocation = menuButton.getLocation();

            System.out.println("✓ Element attributes retrieved:");
//            System.out.println("  - Text: " + elementText);
//            System.out.println("  - Class: " + elementClass);
            System.out.println("  - Enabled: " + elementEnabled);
            System.out.println("  - Displayed: " + elementDisplayed);
            System.out.println("  - Size: " + elementSize);
            System.out.println("  - Location: " + elementLocation);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Test interrupted", e);
        }
    }
}
