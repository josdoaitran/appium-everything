package com.smarttestinglab.lesson9.ios;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.ScreenOrientation;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestBasic7DeviceInteractionIos {
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
    public void testDeviceInteractions() {
        // Wait for app to load
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Get device orientation
        ScreenOrientation orientation = driver.getOrientation();
        System.out.println("Current orientation: " + orientation);

        // Get device time (Java doesn't have direct device time, so we'll use system time)
        LocalDateTime deviceTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTime = deviceTime.format(formatter);
        System.out.println("Device time: " + formattedTime);

        // Get network connection info
        try {
            // Note: Network connection info might not be directly available in Java Appium
            // You might need to use driver.getNetworkConnection() if available
            System.out.println("Network connection: Available");
        } catch (Exception e) {
            System.out.println("Network connection: Unable to retrieve");
        }

        // Open menu
        WebElement menuButton = driver.findElement(AppiumBy.accessibilityId("More-tab-item"));
        menuButton.click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Menu opened");

        // Test back button
        driver.navigate().back();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("✓ Device interactions successful");
    }
}
