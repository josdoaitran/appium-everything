## Common iOS Locator Examples

### Login Form Example:
```java
// Username field
By usernameField = By.accessibilityId("username_field");

// Password field
By passwordField = By.accessibilityId("password_field");

// Login button
By loginButton = By.accessibilityId("login_button");

// Or using name
By loginButton = By.name("Login");
```

### Navigation Example:
```java
// Back button
By backButton = By.accessibilityId("back_button");

// Tab bar items
By homeTab = By.accessibilityId("home_tab");
By profileTab = By.accessibilityId("profile_tab");
```

### Table Example:
```java
// Table cell by index
By firstCell = By.xpath("//XCUIElementTypeCell[1]");

// Table cell by content
By cellWithText = By.xpath("//XCUIElementTypeCell[.//XCUIElementTypeStaticText[@name='Item Name']]");
```

## iOS-Specific Methods

### Keyboard Handling:
```java
// Hide keyboard
driver.hideKeyboard();

// Hide keyboard with strategy
driver.hideKeyboard("Done");
```

### Gestures:
```java
// Tap
driver.tap(1, element, 100);

// Swipe
driver.swipe(startX, startY, endX, endY, duration);

// Scroll
driver.executeScript("mobile: scroll", element);
```

### iOS-Specific Actions:
```java
// Shake device
driver.executeScript("mobile: shake");

// Lock device
driver.lockDevice();

// Unlock device
driver.unlockDevice();
```

## Best Practices for iOS Locators

### 1. Use Accessibility IDs
- Most stable across app updates
- Designed for automation
- Fast execution

### 2. Avoid Dynamic Content
- Don't rely on text that changes
- Use stable identifiers
- Consider using accessibility IDs

### 3. Handle Alerts
```java
// Accept alerts automatically
driver.switchTo().alert().accept();

// Or use capability
// "appium:autoAcceptAlerts": true
```

### 4. Wait Strategies
```java
// Wait for element to be visible
WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
wait.until(ExpectedConditions.visibilityOfElementLocated(By.accessibilityId("element_id")));
```

### 5. Element State Checks
```java
// Check if element is enabled
boolean isEnabled = driver.findElement(By.accessibilityId("button")).isEnabled();

// Check if element is displayed
boolean isDisplayed = driver.findElement(By.accessibilityId("button")).isDisplayed();
```