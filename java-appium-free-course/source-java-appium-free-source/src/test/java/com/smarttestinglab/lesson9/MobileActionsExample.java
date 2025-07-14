package com.smarttestinglab.lesson9;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;
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
import java.util.List;

public class MobileActionsExample {
    private AndroidDriver driver;
    private WebDriverWait wait;

    @BeforeMethod
    public void setUp() throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName("Android")
                .setPlatformVersion("11")
                .setAutomationName("UiAutomator2")
                .setDeviceName("Android Emulator")
                .setApp("path/to/your/app.apk")
                .setNoReset(false)
                .setAutoGrantPermissions(true);

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @Test
    public void testMobileActions() {
        // Basic element interaction
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.accessibilityId("login-button")));
        loginButton.click();

        // Text input
        WebElement usernameField = driver.findElement(AppiumBy.id("username"));
        usernameField.sendKeys("testuser");

        // Get element information
        String buttonText = loginButton.getText();
        boolean isEnabled = loginButton.isEnabled();
        Assert.assertTrue(isEnabled, "Login button should be enabled");

        // Scroll action
        driver.executeScript("mobile: scroll", ImmutableMap.of("direction", "down"));

        // Swipe gesture
        Dimension size = driver.manage().window().getSize();
        TouchAction touchAction = new TouchAction(driver);
        touchAction.press(PointOption.point(size.getWidth() / 2, size.getHeight() / 2))
                   .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                   .moveTo(PointOption.point(size.getWidth() / 2, size.getHeight() / 4))
                   .release()
                   .perform();

        // Long press
        WebElement longPressElement = driver.findElement(AppiumBy.id("long-press-element"));
        touchAction.longPress(ElementOption.element(longPressElement, Duration.ofSeconds(2)))
                   .release()
                   .perform();

        // Handle alert
        try {
            Alert alert = driver.switchTo().alert();
            alert.accept();
        } catch (Exception e) {
            // No alert present
        }

        // Take screenshot
        File screenshot = driver.getScreenshotAs(OutputType.FILE);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
