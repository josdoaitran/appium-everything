## Utility Actions

### 1. Screenshots
```java
// Take screenshot
File screenshot = driver.getScreenshotAs(OutputType.FILE);

// Save screenshot
try {
    FileUtils.copyFile(screenshot, new File("screenshot.png"));
} catch (IOException e) {
    e.printStackTrace();
}

// Take screenshot of specific element
WebElement element = driver.findElement(AppiumBy.id("element-id"));
File elementScreenshot = element.getScreenshotAs(OutputType.FILE);
```

### 2. Page Source
```java
// Get page source (XML)
String pageSource = driver.getPageSource();
System.out.println(pageSource);

// Save page source to file
try {
    FileWriter writer = new FileWriter("page_source.xml");
    writer.write(pageSource);
    writer.close();
} catch (IOException e) {
    e.printStackTrace();
}
```

### 3. Device Information
```java
// Get device time
String deviceTime = driver.getDeviceTime();

// Get device info (Android)
if (driver instanceof AndroidDriver) {
    AndroidDriver androidDriver = (AndroidDriver) driver;
    System.out.println("Current package: " + androidDriver.getCurrentPackage());
    System.out.println("Current activity: " + androidDriver.currentActivity());
}

// Get window size
Dimension windowSize = driver.manage().window().getSize();
System.out.println("Window size: " + windowSize);
```