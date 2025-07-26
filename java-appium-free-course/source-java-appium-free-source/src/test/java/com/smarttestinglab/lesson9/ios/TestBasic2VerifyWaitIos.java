package com.smarttestinglab.lesson9.ios;

import io.appium.java_client.AppiumBy;
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

public class TestBasic2VerifyWaitIos {
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
    public void testVerifyElementVisibility() throws InterruptedException {
        WebElement menuIcon = driver.findElement(AppiumBy.accessibilityId("More-tab-item"));
        menuIcon.click();
        WebElement loginOptionMenu = driver.findElement(AppiumBy.iOSNsPredicateString("name == \"Login Button\""));
        loginOptionMenu.click();
        WebElement username = driver.findElement(AppiumBy.className("XCUIElementTypeTextField"));
        username.sendKeys("bod@example.com");
        WebElement password = driver.findElement(AppiumBy.className("XCUIElementTypeSecureTextField"));
        password.sendKeys("10203040");
        driver.findElement(AppiumBy.accessibilityId("Passwords")).click();
        WebElement loginButton = driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeButton[`name == \"Login\"`]"));
        loginButton.click();
        WebElement productTitle = driver.findElement(AppiumBy.accessibilityId("title"));
        wait.until(ExpectedConditions.visibilityOf(productTitle));
        wait.until(ExpectedConditions.textToBePresentInElement(productTitle, "title"));
//        wait.until(ExpectedConditions.elementToBeClickable(productTitle));
//        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id(APP_PACKAGE + ":id/productTV")));
        Assert.assertTrue(productTitle.isDisplayed());
        Assert.assertEquals(productTitle.getText(), "title");
    }
}
