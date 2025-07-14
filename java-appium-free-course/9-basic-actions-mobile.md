# Lesson 9: Essential Mobile Actions and Interactions with Appium

## Overview
This lesson covers the essential actions you can perform on mobile elements using Appium for both Android and iOS applications.

## Table of Contents
1. [Element Locator Strategies](#element-locator-strategies)
2. [Basic Element Actions](#basic-element-actions)
3. [Advanced Element Interactions](#advanced-element-interactions)
4. [Touch Actions and Gestures](#touch-actions-and-gestures)
5. [Device-Specific Actions](#device-specific-actions)
6. [Wait Strategies](#wait-strategies)
7. [Handling Alerts and Popups](#handling-alerts-and-popups)
8. [Working with Keyboards](#working-with-keyboards)
9. [Context Switching](#context-switching)
10. [Utility Actions](#utility-actions)
11. [Best Practices](#best-practices)

---

## Element Locator Strategies

### 1. AppiumBy Locators (Recommended)
```java
// Accessibility ID (works for both Android and iOS)
driver.findElement(AppiumBy.accessibilityId("login-button"))

// ID (Android specific)
driver.findElement(AppiumBy.id("com.example.app:id/username"))

// XPath (works for both platforms)
driver.findElement(AppiumBy.xpath("//android.widget.Button[@text='Login']"))

// Class Name
driver.findElement(AppiumBy.className("android.widget.EditText"))

// Tag Name
driver.findElement(AppiumBy.tagName("button"))

// Name (iOS specific)
driver.findElement(AppiumBy.name("username"))

// iOS Predicate String (iOS specific)
driver.findElement(AppiumBy.iOSNsPredicateString("type == 'XCUIElementTypeButton' AND name == 'Login'"))

// iOS Class Chain (iOS specific)
driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeButton[`name == 'Login'`]"))

// Android UI Automator (Android specific)
driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Login\")"))
```

### 2. Finding Multiple Elements
```java
// Find all buttons
List<WebElement> buttons = driver.findElements(AppiumBy.className("android.widget.Button"));

// Find all elements with specific text
List<WebElement> elements = driver.findElements(AppiumBy.xpath("//*[@text='Item']"));
```

---

## Basic Element Actions

### 1. Click Operations
```java
// Simple click
WebElement loginButton = driver.findElement(AppiumBy.accessibilityId("login-button"));
loginButton.click();

// Click with wait
WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
WebElement element = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.id("button-id")));
element.click();

// Click at specific coordinates
TouchAction touchAction = new TouchAction(driver);
touchAction.tap(PointOption.point(100, 200)).perform();
```

### 2. Text Input Operations
```java
// Enter text
WebElement usernameField = driver.findElement(AppiumBy.id("username"));
usernameField.sendKeys("testuser");

// Clear and enter text
usernameField.clear();
usernameField.sendKeys("newuser");

// Send special keys
usernameField.sendKeys(Keys.ENTER);
```

### 3. Getting Element Information
```java
// Get text content
String buttonText = loginButton.getText();

// Get attribute value
String isEnabled = loginButton.getAttribute("enabled");
String resourceId = loginButton.getAttribute("resource-id"); // Android
String label = loginButton.getAttribute("label"); // iOS

// Check element state
boolean isDisplayed = loginButton.isDisplayed();
boolean isEnabled = loginButton.isEnabled();
boolean isSelected = loginButton.isSelected();

// Get element size and location
Dimension size = loginButton.getSize();
Point location = loginButton.getLocation();
Rectangle rect = loginButton.getRect();
```

---

## Advanced Element Interactions

### 1. Scrolling Actions
```java
// Scroll down
driver.executeScript("mobile: scroll", ImmutableMap.of("direction", "down"));

// Scroll to element (Android)
driver.executeScript("mobile: scroll", ImmutableMap.of("elementId", element.getId()));

// Scroll to element (iOS)
driver.executeScript("mobile: scroll", ImmutableMap.of("element", element, "direction", "down"));

// Scroll with custom parameters
driver.executeScript("mobile: scroll", ImmutableMap.of(
    "direction", "down",
    "percent", 0.8,
    "speed", 1000
));
```

### 2. Swipe Gestures
```java
// Simple swipe
Dimension size = driver.manage().window().getSize();
int startX = size.getWidth() / 2;
int startY = size.getHeight() / 2;
int endX = startX;
int endY = startY - 200; // Swipe up

TouchAction touchAction = new TouchAction(driver);
touchAction.press(PointOption.point(startX, startY))
           .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
           .moveTo(PointOption.point(endX, endY))
           .release()
           .perform();

// Swipe on specific element
WebElement element = driver.findElement(AppiumBy.id("scrollable-element"));
Rectangle rect = element.getRect();
touchAction.press(PointOption.point(rect.getX() + rect.getWidth()/2, rect.getY() + rect.getHeight()/2))
           .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
           .moveTo(PointOption.point(rect.getX() + rect.getWidth()/2, rect.getY()))
           .release()
           .perform();
```

### 3. Drag and Drop
```java
// Drag and drop between two elements
WebElement source = driver.findElement(AppiumBy.id("source-element"));
WebElement target = driver.findElement(AppiumBy.id("target-element"));

TouchAction touchAction = new TouchAction(driver);
touchAction.longPress(ElementOption.element(source))
           .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
           .moveTo(ElementOption.element(target))
           .release()
           .perform();

// Drag to specific coordinates
Point sourceLocation = source.getLocation();
touchAction.longPress(PointOption.point(sourceLocation.getX(), sourceLocation.getY()))
           .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
           .moveTo(PointOption.point(300, 400))
           .release()
           .perform();
```

---

## Touch Actions and Gestures

### 1. Tap Actions
```java
// Single tap
TouchAction touchAction = new TouchAction(driver);
touchAction.tap(PointOption.point(100, 200)).perform();

// Tap on element
WebElement element = driver.findElement(AppiumBy.id("element-id"));
touchAction.tap(ElementOption.element(element)).perform();

// Double tap
touchAction.tap(PointOption.point(100, 200))
           .waitAction(WaitOptions.waitOptions(Duration.ofMillis(100)))
           .tap(PointOption.point(100, 200))
           .perform();
```

### 2. Long Press Actions
```java
// Long press on element
WebElement element = driver.findElement(AppiumBy.id("element-id"));
TouchAction touchAction = new TouchAction(driver);
touchAction.longPress(ElementOption.element(element, Duration.ofSeconds(2))).release().perform();

// Long press at coordinates
touchAction.longPress(PointOption.point(100, 200))
           .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2)))
           .release()
           .perform();
```

### 3. Multi-Touch Gestures
```java
// Pinch (zoom out)
WebElement element = driver.findElement(AppiumBy.id("zoomable-element"));
Rectangle rect = element.getRect();
int centerX = rect.getX() + rect.getWidth() / 2;
int centerY = rect.getY() + rect.getHeight() / 2;

TouchAction finger1 = new TouchAction(driver);
TouchAction finger2 = new TouchAction(driver);

finger1.press(PointOption.point(centerX - 50, centerY))
       .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
       .moveTo(PointOption.point(centerX - 100, centerY))
       .release();

finger2.press(PointOption.point(centerX + 50, centerY))
       .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
       .moveTo(PointOption.point(centerX + 100, centerY))
       .release();

MultiTouchAction multiTouch = new MultiTouchAction(driver);
multiTouch.add(finger1).add(finger2).perform();
```

---

## Device-Specific Actions

### 1. Android Device Actions
```java
// Press back button
((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.BACK));

// Press home button
((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.HOME));

// Press menu button
((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.MENU));

// Open notifications
((AndroidDriver) driver).openNotifications();

// Start activity
((AndroidDriver) driver).startActivity(new Activity("com.example.app", ".MainActivity"));

// Get current activity
String currentActivity = ((AndroidDriver) driver).currentActivity();

// Get current package
String currentPackage = ((AndroidDriver) driver).getCurrentPackage();
```

### 2. iOS Device Actions
```java
// Shake device
((IOSDriver) driver).shake();

// Lock device
((IOSDriver) driver).lockDevice();

// Unlock device
((IOSDriver) driver).unlockDevice();

// Background app
((IOSDriver) driver).runAppInBackground(Duration.ofSeconds(5));

// Get battery info
BatteryInfo batteryInfo = driver.getBatteryInfo();
```

### 3. Common Device Actions
```java
// Rotate device
driver.rotate(ScreenOrientation.LANDSCAPE);
driver.rotate(ScreenOrientation.PORTRAIT);

// Get orientation
ScreenOrientation orientation = driver.getOrientation();

// Hide keyboard
driver.hideKeyboard();

// Take screenshot
File screenshot = driver.getScreenshotAs(OutputType.FILE);
```

---

## Wait Strategies

### 1. WebDriverWait (Recommended)
```java
WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

// Wait for element to be visible
WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(
    AppiumBy.id("element-id")));

// Wait for element to be clickable
WebElement clickableElement = wait.until(ExpectedConditions.elementToBeClickable(
    AppiumBy.accessibilityId("button")));

// Wait for text to be present
wait.until(ExpectedConditions.textToBePresentInElement(element, "Expected Text"));

// Wait for element to disappear
wait.until(ExpectedConditions.invisibilityOfElementLocated(AppiumBy.id("loading")));
```

### 2. FluentWait
```java
FluentWait<WebDriver> fluentWait = new FluentWait<>(driver)
    .withTimeout(Duration.ofSeconds(30))
    .pollingEvery(Duration.ofSeconds(2))
    .ignoring(NoSuchElementException.class);

WebElement element = fluentWait.until(driver -> {
    return driver.findElement(AppiumBy.id("dynamic-element"));
});
```

### 3. Custom Wait Conditions
```java
// Wait for element to contain specific text
wait.until(new ExpectedCondition<Boolean>() {
    public Boolean apply(WebDriver driver) {
        WebElement element = driver.findElement(AppiumBy.id("status"));
        return element.getText().contains("Success");
    }
});

// Wait for element attribute to have specific value
wait.until(new ExpectedCondition<Boolean>() {
    public Boolean apply(WebDriver driver) {
        WebElement element = driver.findElement(AppiumBy.id("progress"));
        return "100".equals(element.getAttribute("value"));
    }
});
```

---

## Handling Alerts and Popups

### 1. System Alerts
```java
// Switch to alert
Alert alert = driver.switchTo().alert();

// Get alert text
String alertText = alert.getText();

// Accept alert (OK button)
alert.accept();

// Dismiss alert (Cancel button)
alert.dismiss();

// Send text to alert (if it has input field)
alert.sendKeys("Alert input text");
```

### 2. App Permissions (Android)
```java
// Handle permission dialogs
try {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    WebElement allowButton = wait.until(ExpectedConditions.elementToBeClickable(
        AppiumBy.id("com.android.permissioncontroller:id/permission_allow_button")));
    allowButton.click();
} catch (TimeoutException e) {
    // Permission dialog didn't appear
}
```

### 3. iOS Permissions
```java
// Handle iOS permission alerts
try {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    WebElement allowButton = wait.until(ExpectedConditions.elementToBeClickable(
        AppiumBy.accessibilityId("Allow")));
    allowButton.click();
} catch (TimeoutException e) {
    // Permission dialog didn't appear
}
```

---

## Working with Keyboards

### 1. Keyboard Actions
```java
// Show keyboard (if not visible)
driver.executeScript("mobile: type", ImmutableMap.of("text", ""));

// Hide keyboard
driver.hideKeyboard();

// Check if keyboard is shown
boolean isKeyboardShown = driver.isKeyboardShown();

// Send keys to active element
driver.getKeyboard().sendKeys("Hello World");

// Press specific keys
driver.getKeyboard().pressKey(Keys.ENTER);
driver.getKeyboard().pressKey(Keys.BACK_SPACE);
```

### 2. Platform-Specific Keyboard Handling
```java
// Android - Hide keyboard by pressing back
((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.BACK));

// iOS - Hide keyboard by tapping return
driver.findElement(AppiumBy.accessibilityId("Return")).click();

// iOS - Hide keyboard by tapping outside
TouchAction touchAction = new TouchAction((IOSDriver) driver);
touchAction.tap(PointOption.point(100, 100)).perform();
```

---

## Context Switching

### 1. Native vs WebView Context
```java
// Get all contexts
Set<String> contexts = driver.getContextHandles();
System.out.println("Available contexts: " + contexts);

// Switch to WebView context
for (String context : contexts) {
    if (context.contains("WEBVIEW")) {
        driver.context(context);
        break;
    }
}

// Switch back to native context
driver.context("NATIVE_APP");

// Get current context
String currentContext = driver.getContext();
```

### 2. Working with WebView Elements
```java
// Switch to WebView
driver.context("WEBVIEW_com.example.app");

// Now you can use standard Selenium WebDriver methods
WebElement webElement = driver.findElement(By.id("web-element-id"));
webElement.click();

// Switch back to native
driver.context("NATIVE_APP");
```

---


## Best Practices

### 1. Element Identification
```java
// Prefer accessibility ID over other locators
WebElement element = driver.findElement(AppiumBy.accessibilityId("login-button"));

// Use resource-id for Android when accessibility ID is not available
WebElement androidElement = driver.findElement(AppiumBy.id("com.example.app:id/login"));

// Use name for iOS when accessibility ID is not available
WebElement iosElement = driver.findElement(AppiumBy.name("Login"));

// Avoid XPath when possible (slower and fragile)
// Use only when other locators are not available
WebElement xpathElement = driver.findElement(AppiumBy.xpath("//android.widget.Button[@text='Login']"));
```

### 2. Wait Strategies
```java
// Always use explicit waits instead of Thread.sleep()
WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

// Set implicit wait at the beginning of test
driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

// Use appropriate wait conditions
wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.id("button")));
wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("element")));
wait.until(ExpectedConditions.textToBePresentInElement(element, "Expected Text"));
```

### 3. Error Handling
```java
// Handle common exceptions
try {
    WebElement element = driver.findElement(AppiumBy.id("element-id"));
    element.click();
} catch (NoSuchElementException e) {
    System.out.println("Element not found: " + e.getMessage());
} catch (TimeoutException e) {
    System.out.println("Timeout waiting for element: " + e.getMessage());
} catch (WebDriverException e) {
    System.out.println("WebDriver error: " + e.getMessage());
}
```
---

## Example Test Class

[Click Here](./source-java-appium-free-source/src/test/java/com/smarttestinglab/lesson9/MobileActionsExample.java)

---

## Summary

This lesson covered the essential mobile actions you can perform with Appium:

1. **Element Locators**: Various strategies for finding elements on mobile apps
2. **Basic Actions**: Click, send keys, get text, and element state checks
3. **Advanced Interactions**: Scrolling, swiping, drag and drop
4. **Touch Gestures**: Tap, long press, multi-touch actions
5. **Device Actions**: Platform-specific device interactions
6. **Wait Strategies**: Proper synchronization techniques
7. **Alerts and Popups**: Handling system dialogs and permissions
8. **Keyboard Handling**: Working with virtual keyboards
9. **Context Switching**: Native vs WebView contexts
10. **Utility Actions**: Screenshots, page source, device info

These actions form the foundation for creating robust mobile test automation scripts. Practice these concepts with your own applications to become proficient in mobile test automation with Appium.

## Next Steps

- Practice implementing these actions in your test scripts
- Explore advanced gestures and animations
- Learn about parallel execution and device farms
- Study performance testing with mobile applications
- Investigate continuous integration with mobile tests
