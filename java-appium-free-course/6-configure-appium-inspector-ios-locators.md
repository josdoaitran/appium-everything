# Steps to setup Appium Server and Appium Inspector
- Install XCUITest Driver
- Start Appium in MacOSX
- Setup Capability to inspect locator

## Command to install XCUITest

```
❯ appium driver list --installed
❯ appium driver install xcuitest
✔ Checking if 'appium-xcuitest-driver' is compatible
✔ Installing 'xcuitest'
ℹ Driver xcuitest@9.9.4 successfully installed
- automationName: XCUITest
- platformNames: ["iOS","tvOS"]
```

After, installed successfully. You expect to receive this result to check you installed successfully or not.

```
appium driver list
   ✔ Listing available drivers
   - uiautomator2@4.2.3 [installed (npm)]
   - xcuitest [installed (npm)]
```
## Configure iOS Simulator SDK
You can run these command:
```
❯ xcode-select --print-path
/Library/Developer/CommandLineTools
❯ xcodebuild -version
xcode-select: error: tool 'xcodebuild' requires Xcode, but active developer directory '/Library/Developer/CommandLineTools' is a command line tools instance
❯ xcrun simctl runtime list
xcrun: error: unable to find utility "simctl", not a developer tool or in PATH
❯ xcode-select --print-path
/Library/Developer/CommandLineTools
❯ xcodebuild -version
xcode-select: error: tool 'xcodebuild' requires Xcode, but active developer directory '/Library/Developer/CommandLineTools' is a command line tools instance
❯ sudo xcode-select --switch /Applications/Xcode.app/Contents/Developer
Password:
❯ sudo xcodebuild -license accept
```

## Start Appium Server
- Open Termninal in your Mac Machine
- Run this command:
```
appium --allow-cors
```

## Setup Capability to inspect locator
Fill in the following capabilities in Appium Inspector:

### Required Capabilities:
```json
{
  "platformName": "iOS",
  "appium:automationName": "XCUITest",
  "appium:appPackage": "com.saucelabs.mydemoapp.ios",
  "appium:app": "/Users/doaitran/Documents/Personal/Smart Testing Lab/mda-2.2.0-25.apk",
  "appium:noReset": true
}
```
## General iOS element Locator types

iOS apps use different locator strategies compared to Android. Here are the main locator types available for iOS testing:

### 1. Accessibility ID (Recommended)
Most reliable locator for iOS apps. Uses the `accessibilityIdentifier` property.

```java
// In Java
By elementId = By.accessibilityId("login_button");

// In Appium Inspector
// Locator: accessibility id
// Value: login_button
```

### 2. Class Name
Uses the iOS element class name.

```java
// In Java
By elementClass = By.className("XCUIElementTypeButton");

// In Appium Inspector
// Locator: class name
// Value: XCUIElementTypeButton
```

### 3. Name (Accessibility Label)
Uses the `accessibilityLabel` property of the element.

```java
// In Java
By elementName = By.name("Login");

// In Appium Inspector
// Locator: name
// Value: Login
```

### 4. XPath
Uses XPath expressions to locate elements.

```java
// In Java
By elementXPath = By.xpath("//XCUIElementTypeButton[@name='Login']");

// In Appium Inspector
// Locator: xpath
// Value: //XCUIElementTypeButton[@name='Login']
```

### 5. Predicate String
iOS-specific locator using NSPredicate syntax.

```java
// In Java
By elementPredicate = By.iOSNsPredicateString("type == 'XCUIElementTypeButton' AND name == 'Login'");

// In Appium Inspector
// Locator: -ios predicate string
// Value: type == 'XCUIElementTypeButton' AND name == 'Login'
```

### 6. Class Chain
iOS-specific locator using XCUITest's class chain syntax.

```java
// In Java
By elementClassChain = By.iOSClassChain("**/XCUIElementTypeButton[`name == 'Login'`]");

// In Appium Inspector
// Locator: -ios class chain
// Value: **/XCUIElementTypeButton[`name == 'Login'`]
```

## iOS Element Types

Common iOS element types you'll encounter:

- `XCUIElementTypeButton` - Buttons
- `XCUIElementTypeTextField` - Text input fields
- `XCUIElementTypeStaticText` - Text labels
- `XCUIElementTypeTable` - Tables
- `XCUIElementTypeCell` - Table cells
- `XCUIElementTypeNavigationBar` - Navigation bars
- `XCUIElementTypeTabBar` - Tab bars
- `XCUIElementTypeAlert` - Alert dialogs
- `XCUIElementTypeSwitch` - Toggle switches
- `XCUIElementTypeSlider` - Sliders
- `XCUIElementTypePicker` - Pickers
- `XCUIElementTypeDatePicker` - Date pickers

## Locator Priority for iOS

1. **Accessibility ID** (Most reliable)
2. **Name** (Accessibility Label)
3. **Predicate String** (Flexible and powerful)
4. **Class Chain** (Good for complex hierarchies)
5. **XPath** (Universal but slower)
6. **Class Name** (Least specific)

## Using Appium Inspector for iOS

### Step 1: Start Session
1. Open Appium Inspector
2. Configure capabilities for iOS
3. Click "Start Session"

### Step 2: Inspect Elements
1. Click on any element in the app
2. Appium Inspector will highlight the element
3. View element details in the right panel:
   - Accessibility ID
   - Name (Accessibility Label)
   - Class Name
   - Type
   - Value
   - Enabled/Disabled state

### Step 3: Generate Locators
1. Select an element
2. Choose the locator strategy
3. Copy the generated locator
4. Test the locator using the search feature

## iOS-Specific Capabilities

Update your capabilities for iOS testing:

```json
{
  "platformName": "iOS",
  "appium:automationName": "XCUITest",
  "appium:deviceName": "iPhone 14",
  "appium:platformVersion": "17.0",
  "appium:app": "/path/to/your/app.app",
  "appium:noReset": true,
  "appium:autoAcceptAlerts": true,
  "appium:newCommandTimeout": 60,
  "appium:wdaLaunchTimeout": 30,
  "appium:wdaConnectionTimeout": 30
}
```

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

## Troubleshooting iOS Locators

### Common Issues:

1. **Element not found**:
   - Check if element is visible
   - Verify locator strategy
   - Try different locator types

2. **Element not clickable**:
   - Check if element is enabled
   - Verify element bounds
   - Try tapping center of element

3. **Dynamic content**:
   - Use stable identifiers
   - Avoid text-based locators
   - Consider using accessibility IDs

4. **Performance issues**:
   - Use accessibility IDs when possible
   - Avoid complex XPath expressions
   - Cache frequently used locators

## Error - Missing Appium Driver XCUITest
- If you miss install XCUITest Driver for your Appium Server, you will receive the error as bellow
![](./images/appium-inspector-error-missing-xcuitest.png)

- Solution: Install Appium XCUITest Driver
```
❯ appium driver install xcuitest
✔ Checking if 'appium-xcuitest-driver' is compatible
✔ Installing 'xcuitest'
ℹ Driver xcuitest@9.9.4 successfully installed
- automationName: XCUITest
- platformNames: ["iOS","tvOS"]
```

## Error - Appium Inspector CORS error
- If you receive the error popup in Appium Inspector related to `--allow cors`
![](./images/appium-inspector-error-allow-cors.png)
- Please start appium server with this params `--allow cors`
```
appium --allow-cors
```
## Error - Missing  iOS Simulator SDK
- If you don't open your simulator device yet, you will receive the error popup
![](./images/appium-inspector-error-ios-sdk-simulator.png)
- You can run these command:
```
❯ xcode-select --print-path
/Library/Developer/CommandLineTools
❯ xcodebuild -version
xcode-select: error: tool 'xcodebuild' requires Xcode, but active developer directory '/Library/Developer/CommandLineTools' is a command line tools instance
❯ xcrun simctl runtime list
xcrun: error: unable to find utility "simctl", not a developer tool or in PATH
❯ xcode-select --print-path
/Library/Developer/CommandLineTools
❯ xcodebuild -version
xcode-select: error: tool 'xcodebuild' requires Xcode, but active developer directory '/Library/Developer/CommandLineTools' is a command line tools instance
❯ sudo xcode-select --switch /Applications/Xcode.app/Contents/Developer
Password:
❯ sudo xcodebuild -license accept
```
- Reset Appium Server

## Error - Wrong iOS Version
- You will receive this error pop-up if input invalid iOS version
![](./images/appium-inspector-ios-wrong-version.png)

## Error - Wrong app location
- You will receive this error pop-up if input wrong app location or inacessible location
![](./images/appium-inspector-wrong-locator-app.png)