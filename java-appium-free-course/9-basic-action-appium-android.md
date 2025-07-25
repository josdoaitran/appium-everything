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

### 3. Swipe Gestures
Implementing swipe actions for navigation and scrolling:

```python
# Swipe from bottom to top
actions = ActionChains(driver)
actions.w3c_actions = ActionBuilder(driver, mouse=PointerInput(interaction.POINTER_TOUCH, "touch"))
actions.w3c_actions.pointer_action.move_to_location(start_x, start_y)
actions.w3c_actions.pointer_action.pointer_down()
actions.w3c_actions.pointer_action.move_to_location(end_x, end_y)
actions.w3c_actions.pointer_action.pointer_up()
actions.perform()
```

### 4. Long Press Actions
Performing long press for context menus or special actions:

```python
# Long press action
actions = ActionChains(driver)
actions.w3c_actions = ActionBuilder(driver, mouse=PointerInput(interaction.POINTER_TOUCH, "touch"))
actions.w3c_actions.pointer_action.move_to_location(element.location['x'], element.location['y'])
actions.w3c_actions.pointer_action.pointer_down()
actions.w3c_actions.pointer_action.pause(2.0)
actions.w3c_actions.pointer_action.pointer_up()
actions.perform()
```

### 5. Scroll Actions
Platform-specific scrolling techniques:

**Android:**
```python
# Android UiScrollable
driver.find_element(AppiumBy.ANDROID_UIAUTOMATOR, 
                   'new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text("Target Text"))')
```

**iOS:**
```python
# iOS predicate scrolling
driver.find_element(AppiumBy.IOS_PREDICATE, 'name == "Target Element"')
```

### 6. Wait Strategies
Implementing explicit waits for better reliability:

```python
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

# Wait for element to be clickable
wait = WebDriverWait(driver, 10)
element = wait.until(EC.element_to_be_clickable((AppiumBy.ACCESSIBILITY_ID, "element_id")))
```

### 7. Element Attributes
Getting element properties and attributes:

```python
# Get various attributes
element_text = element.get_attribute("text")
element_enabled = element.get_attribute("enabled")
element_displayed = element.is_displayed()
element_size = element.size
element_location = element.location
```

### 8. Screenshots
Capturing screenshots for debugging and reporting:

```python
# Take screenshot
driver.save_screenshot("screenshot.png")
```

### 9. Device Interactions
Platform-specific device actions:

```python
# Get device orientation
orientation = driver.orientation

# Get device time
device_time = driver.device_time

# Android back button
driver.back()

# iOS shake gesture
driver.shake()
```

### 10. Multiple Elements
Working with collections of elements:

```python
# Find multiple elements
elements = driver.find_elements(AppiumBy.XPATH, "//element_xpath")

# Iterate through elements
for element in elements:
    if element.is_displayed():
        element.click()
        break
```

## Platform Differences

### Android Specific
- Use `AppiumBy.ANDROID_UIAUTOMATOR` for complex selectors
- Use `AppiumBy.ID` with full resource ID
- Back button navigation with `driver.back()`
- Network connection management

### iOS Specific
- Use `AppiumBy.IOS_PREDICATE` for iOS-specific selectors
- Use `AppiumBy.IOS_CLASS_CHAIN` for complex hierarchies
- Shake gesture with `driver.shake()`
- Alert handling with system alerts
- Keyboard management with `driver.hide_keyboard()`

## Best Practices

1. **Use Explicit Waits**: Always use explicit waits instead of fixed sleep times
2. **Element Identification**: Use stable locators (accessibility IDs are preferred)
3. **Error Handling**: Implement try-catch blocks for robust test execution
4. **Screenshots**: Take screenshots on failures for debugging
5. **Platform Abstraction**: Create helper methods for platform-specific actions
6. **Resource Management**: Always quit drivers in teardown methods

## Common Patterns

### Conditional Actions
```python
# Check element state before action
if element.is_displayed() and element.is_enabled():
    element.click()
```

### Retry Logic
```python
# Implement retry for flaky actions
for attempt in range(3):
    try:
        element.click()
        break
    except:
        time.sleep(1)
```

### Page Object Pattern
```python
class LoginPage:
    def __init__(self, driver):
        self.driver = driver
    
    def login(self, username, password):
        self.driver.find_element(AppiumBy.ID, "username").send_keys(username)
        self.driver.find_element(AppiumBy.ID, "password").send_keys(password)
        self.driver.find_element(AppiumBy.ID, "login_button").click()
```

## Running the Tests

To run the basic actions tests:

```bash
# Run Android tests
pytest src/9_basic_actions_mobile_app/test_basic_actions_android.py -v

# Run iOS tests
pytest src/9_basic_actions_mobile_app/test_basic_actions_ios.py -v

# Run specific test
pytest src/9_basic_actions_mobile_app/test_basic_actions_android.py::test_basic_tap_action -v
```

## Next Steps

After mastering these basic actions, you can move on to:
- Advanced gestures and multi-touch actions
- Page Object Model implementation
- Data-driven testing
- Cross-platform test automation
- Performance testing
- CI/CD integration

## Troubleshooting

### Common Issues
1. **Element Not Found**: Check locator strategy and wait conditions
2. **Stale Element**: Re-find elements after page navigation
3. **Timing Issues**: Use explicit waits instead of fixed delays
4. **Platform Differences**: Implement platform-specific logic
5. **App State**: Ensure app is in expected state before actions

### Debug Tips
- Use Appium Inspector to identify elements
- Add logging for test execution flow
- Take screenshots at key points
- Use driver.page_source for HTML debugging
- Check Appium server logs for errors
