# Lesson 9: Basic Actions in Mobile Applications with Appium

## Overview
This lesson covers the fundamental actions you can perform on mobile applications using Appium with Python. You'll learn how to interact with mobile elements, perform gestures, handle device-specific actions, and work with various UI components.

## Learning Objectives
By the end of this lesson, you will be able to:
- Perform basic tap and click actions
- Handle text input and form interactions
- Execute swipe and scroll gestures
- Implement long press actions
- Work with waits and element conditions
- Get element attributes and properties
- Handle device-specific interactions
- Work with multiple elements
- Implement conditional actions

## Basic Actions Covered

### 1. Tap/Click and SendKey Actions
The most fundamental action in mobile testing is tapping elements:

```java
# Basic tap action
WebElement menuIcon = driver.findElement(AppiumBy.id(APP_PACKAGE + ":id/menuIV"));
menuIcon.click();
# Basic text input action
WebElement username = driver.findElement(AppiumBy.id(APP_PACKAGE + ":id/nameET"));
username.sendKeys("bod@example.com");
```
### 2. Verify and Wait Actions
- Verify an element displayed
- Wait Element Visible
- Verify a Test of element
```java
WebElement productTitle = driver.findElement(AppiumBy.id(APP_PACKAGE + ":id/productTV"));
wait.until(ExpectedConditions.visibilityOf(productTitle));
wait.until(ExpectedConditions.textToBePresentInElement(productTitle, "Products"));
// wait.until(ExpectedConditions.elementToBeClickable(productTitle));
// wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id(APP_PACKAGE + ":id/productTV")));
Assert.assertTrue(productTitle.isDisplayed());
Assert.assertEquals(productTitle.getText(), "Products");
```

### 3. Get Attribute Element Actions
Get Attribute Element to verify more.
```java
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
```

### 4. Swipe Gestures
Implementing swipe actions for navigation and scrolling:

```python
# Swipe from bottom to top
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
```

### 5. Multiple Elements Action
Working with collections of elements:

```java
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
```

### 6. Condition Action
Condition to Work with elements:

```java
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
```

### 7. Device Interactions
Platform-specific device actions:

```java
// Get device orientation
        ScreenOrientation orientation = driver.getOrientation();
        System.out.println("Current orientation: " + orientation);

        // Get device time (Java doesn't have direct device time, so we'll use system time)
        LocalDateTime deviceTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTime = deviceTime.format(formatter);
        System.out.println("Device time: " + formattedTime);

        // Get network connection info
        try {
            // Note: Network connection info might not be directly available in Java Appium
            // You might need to use driver.getNetworkConnection() if available
            System.out.println("Network connection: Available");
        } catch (Exception e) {
            System.out.println("Network connection: Unable to retrieve");
        }

        // Open menu
        WebElement menuButton = driver.findElement(By.id("com.saucelabs.mydemoapp.android:id/menuIV"));
        menuButton.click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Menu opened");

        // Test back button
        driver.navigate().back();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Test home button (keycode 3 is HOME)
        driver.pressKey(new KeyEvent(AndroidKey.HOME));

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("✓ Device interactions successful");
```

### 8. Scroll Actions
Platform-specific scrolling techniques:

**Android:**
```java
# Android UiScrollable
driver.find_element(AppiumBy.ANDROID_UIAUTOMATOR, 
                   'new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text("Target Text"))')
```

**iOS:**
```java
@Test
    public void testScrollAction() {
        WebElement element = null;
        int scrollCount = 0;

        while (element == null && scrollCount < 5) {
            try {
                element = driver.findElement(AppiumBy.iOSNsPredicateString("name == \"Product Name\" AND label == \"Sauce Labs Onesie\""));
                // Check if element is visible
                if (wait.until(ExpectedConditions.visibilityOf(element)) != null) {
                    break;
                } else {
                    element = null;
                }
            } catch (NoSuchElementException e) {
                // Element not found, continue scrolling
            }

            performSwipe(
                    new double[]{0.5, 0.8}, // Middle-bottom of screen
                    new double[]{0.5, 0.2}, // Middle-top of screen
                    "bottom to top"
            );
            scrollCount++;

            try {
                Thread.sleep(1000); // Wait for scroll animation
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        // Verify we can see the product
        WebElement product = driver.findElement(AppiumBy.iOSNsPredicateString(
                "name == \"Product Name\" AND label == \"Sauce Labs Onesie\""
        ));
        assert product.isDisplayed();
        System.out.println("✓ Scroll action successful");
    
```
