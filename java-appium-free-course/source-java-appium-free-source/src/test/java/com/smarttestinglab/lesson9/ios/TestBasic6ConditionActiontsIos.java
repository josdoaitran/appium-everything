package com.smarttestinglab.lesson9.ios;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
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
import java.util.List;

public class TestBasic6ConditionActiontsIos {
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
