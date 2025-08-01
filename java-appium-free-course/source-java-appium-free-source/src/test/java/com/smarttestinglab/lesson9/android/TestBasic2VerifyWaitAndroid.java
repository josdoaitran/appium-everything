package com.smarttestinglab.lesson9.android;

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

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class TestBasic2VerifyWaitAndroid {
    private AndroidDriver driver;
    private WebDriverWait wait;

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
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        System.out.println("Test setup completed successfully");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testVerifyElementVisibility() throws InterruptedException {
        WebElement menuIcon = driver.findElement(AppiumBy.id(APP_PACKAGE + ":id/menuIV"));
        menuIcon.click();
        WebElement loginOptionMenu = driver.findElement(AppiumBy.accessibilityId("Login Menu Item"));
        loginOptionMenu.click();
        WebElement username = driver.findElement(AppiumBy.id(APP_PACKAGE + ":id/nameET"));
        username.sendKeys("bod@example.com");
        WebElement password = driver.findElement(AppiumBy.id(APP_PACKAGE + ":id/passwordET"));
        password.sendKeys("10203040");
        WebElement loginButton = driver.findElement(AppiumBy.id(APP_PACKAGE + ":id/loginBtn"));
        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        loginButton.click();
        WebElement productTitle = driver.findElement(AppiumBy.id(APP_PACKAGE + ":id/productTV"));
        wait.until(ExpectedConditions.visibilityOf(productTitle));
        wait.until(ExpectedConditions.textToBePresentInElement(productTitle, "Products"));
//        wait.until(ExpectedConditions.elementToBeClickable(productTitle));
//        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id(APP_PACKAGE + ":id/productTV")));
        Assert.assertTrue(productTitle.isDisplayed());
        Assert.assertEquals(productTitle.getText(), "Products");
    }
}
