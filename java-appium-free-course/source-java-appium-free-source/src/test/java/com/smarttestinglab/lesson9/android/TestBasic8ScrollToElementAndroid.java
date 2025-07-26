package com.smarttestinglab.lesson9.android;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestBasic8ScrollToElementAndroid {
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
    public void testScrollAction() {
        // Scroll to find a specific product
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text(\"Sauce Labs Onesie\"))"
        ));

        // Verify we can see the product
        WebElement product = driver.findElement(By.xpath(
                "//android.widget.TextView[@content-desc='Product Title' and @text='Sauce Labs Onesie']"
        ));

        assert product.isDisplayed();
        System.out.println("✓ Scroll action successful");
    }
}
