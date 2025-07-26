package com.smarttestinglab.lesson9.android;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

public class TestBasic6ConditionActionAndroid {
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
    public void testConditionalActions() {
        // Wait for app to load
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Check if menu is already open
        try {
            WebElement loginMenu = driver.findElement(AppiumBy.accessibilityId("Login Menu Item"));
            if (loginMenu.isDisplayed()) {
                System.out.println("Menu is already open");
            } else {
                // Open menu if not already open
                WebElement menuButton = driver.findElement(By.id("com.saucelabs.mydemoapp.android:id/menuIV"));
                menuButton.click();

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                System.out.println("Menu opened");
            }
        } catch (NoSuchElementException e) {
            // Menu is not open, so open it
            WebElement menuButton = driver.findElement(By.id("com.saucelabs.mydemoapp.android:id/menuIV"));
            menuButton.click();

            try {
                Thread.sleep(2000);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
            System.out.println("Menu opened");
        }

        System.out.println("✓ Conditional actions successful");
    }
}
