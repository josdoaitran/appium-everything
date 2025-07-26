package com.smarttestinglab.lesson9.ios;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.openqa.selenium.Dimension;
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

public class TestBasic4SwipeIos {
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

    /**
     * Helper method to perform a swipe gesture
     *
     * @param startPoint Tuple with (start_x_percent, start_y_percent) as percentages of screen dimensions
     * @param endPoint Tuple with (end_x_percent, end_y_percent) as percentages of screen dimensions
     * @param directionName String describing the swipe direction for logging
     */
    private void performSwipe(double[] startPoint, double[] endPoint, String directionName) {
        try {
            Thread.sleep(3000); // Wait for 3 seconds

            // Get screen dimensions
            Dimension screenSize = driver.manage().window().getSize();
            int width = screenSize.getWidth();
            int height = screenSize.getHeight();

            // Calculate start and end coordinates based on percentages
            int startX = (int) (width * startPoint[0]);
            int startY = (int) (height * startPoint[1]);
            int endX = (int) (width * endPoint[0]);
            int endY = (int) (height * endPoint[1]);

            // Create touch action for swipe using PointerInput
            PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
            Sequence sequence = new Sequence(finger, 1)
                    .addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
                    .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                    .addAction(finger.createPointerMove(Duration.ofMillis(600), PointerInput.Origin.viewport(), endX, endY))
                    .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

            driver.perform(Arrays.asList(sequence));

            Thread.sleep(2000); // Wait for 2 seconds
            System.out.println("✓ Swipe " + directionName + " gesture action successful");

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Swipe action interrupted", e);
        }
    }

    @Test
    public void testSwipe() {
        // Test swipe gesture from bottom to top (scroll up)
        performSwipe(
                new double[]{0.5, 0.8}, // Middle-bottom of screen
                new double[]{0.5, 0.2}, // Middle-top of screen
                "bottom to top"
        );

        // Test swipe gesture from top to bottom (scroll down)
        performSwipe(
                new double[]{0.5, 0.2}, // Middle-top of screen
                new double[]{0.5, 0.8}, // Middle-bottom of screen
                "top to bottom"
        );
    }

    // Alternative test methods for individual swipe directions
    @Test
    public void testSwipeBottomToTop() {
        performSwipe(
                new double[]{0.5, 0.8}, // Middle-bottom of screen
                new double[]{0.5, 0.2}, // Middle-top of screen
                "bottom to top"
        );
    }

    @Test
    public void testSwipeTopToBottom() {
        performSwipe(
                new double[]{0.5, 0.2}, // Middle-top of screen
                new double[]{0.5, 0.8}, // Middle-bottom of screen
                "top to bottom"
        );
    }
}
