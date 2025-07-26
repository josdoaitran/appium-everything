package com.smarttestinglab.lesson9.android;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;

public class TestBasic4SwipeAndroid {
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
