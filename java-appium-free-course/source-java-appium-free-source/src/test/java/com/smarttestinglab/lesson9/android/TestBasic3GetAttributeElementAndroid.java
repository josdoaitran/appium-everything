package com.smarttestinglab.lesson9.android;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class TestBasic3GetAttributeElementAndroid {
    private AndroidDriver driver;
    private static final String APP_PACKAGE = "com.saucelabs.mydemoapp.android";
    private static final String APP_ACTIVITY = "com.saucelabs.mydemoapp.android.view.activities.MainActivity";
    private static final String APPIUM_SERVER_URL = "http://127.0.0.1:4723";
    private static final String APP_DIRECTORY = "apps/mda-2.2.0-25.apk";
    public static String getAppDirectory() {
        File file = new File(APP_DIRECTORY);
        return file.getAbsolutePath();
    }
    @BeforeMethod
    public void setUp() throws MalformedURLException {
        // Configure UiAutomator2 options for Android
        UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName("Android")
                .setPlatformVersion("11")
                .setAutomationName("UiAutomator2")
                .setDeviceName("Android Emulator")
                .setApp(getAppDirectory())
                .setNoReset(false)
                .setAutoGrantPermissions(true)
                .setAppPackage(APP_PACKAGE)
                .setAppWaitActivity(APP_ACTIVITY);
        // Initialize AndroidDriver
        driver = new AndroidDriver(new URL(APPIUM_SERVER_URL), options);
        // Initialize WebDriverWait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        System.out.println("Test setup completed successfully");
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
            WebElement menuButton = driver.findElement(By.id("com.saucelabs.mydemoapp.android:id/menuIV"));

            // Get various attributes
            String elementText = menuButton.getAttribute("text");
            String elementClass = menuButton.getAttribute("class");
            String elementEnabled = menuButton.getAttribute("enabled");
            boolean elementDisplayed = menuButton.isDisplayed();
            Dimension elementSize = menuButton.getSize();
            Point elementLocation = menuButton.getLocation();

            System.out.println("✓ Element attributes retrieved:");
            System.out.println("  - Text: " + elementText);
            System.out.println("  - Class: " + elementClass);
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
