package com.smarttestinglab.lesson9.ios;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
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

public class TestBasic5MultiElementsIos {
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
    public void testMultipleElements() {
        // Find all product cards
        List<WebElement> products = driver.findElements(
                By.xpath("//XCUIElementTypeCollectionView/XCUIElementTypeCell")
        );

        System.out.println("Found " + products.size() + " products");

        // Click on the first product
        if (!products.isEmpty()) {
            WebElement firstProduct = products.get(0);
            firstProduct.click();

            try {
                Thread.sleep(2000); // Wait for 2 seconds
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            // Verify we're on product detail page
            WebElement addToCartButton = driver.findElement(
                    AppiumBy.iOSNsPredicateString("name == \"AddToCart\"")
            );

            assert addToCartButton.isDisplayed();
            System.out.println("✓ Multiple elements interaction successful");
        }
    }
}
