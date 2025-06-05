# Setting Up Java Maven Project with Appium for iOS Testing

## Prerequisites
- macOS operating system (required for iOS testing)
- Java JDK (version 8 or higher) installed
- Maven installed
- Xcode installed
- iOS Simulator or physical iOS device
- Appium Server installed
- IDE (IntelliJ IDEA or Eclipse) installed
- Apple Developer account (free account is sufficient for simulator testing)
- WebDriverAgent installed (comes with Appium)

## Step 1: Create a New Maven Project
1. Open your IDE (IntelliJ IDEA recommended)
2. Create a new Maven project:
   - Click "File" → "New" → "Project"
   - Select "Maven"
   - Choose "Create from archetype" and select "maven-archetype-quickstart"
   - Fill in the project details:
     ```
     GroupId: com.yourcompany
     ArtifactId: appium-ios-test
     Version: 1.0-SNAPSHOT
     ```

## Step 2: Configure pom.xml
Add the following dependencies to your `pom.xml`:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.yourcompany</groupId>
    <artifactId>appium-ios-test</artifactId>
    <version>1.0-SNAPSHOT</version>

    <properties>
        <maven.compiler.source>11</maven.compiler.source>
        <maven.compiler.target>11</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <appium.version>9.0.0</appium.version>
        <testng.version>7.7.1</testng.version>
        <selenium.version>4.10.0</selenium.version>
    </properties>

    <dependencies>
        <!-- Appium Java Client -->
        <dependency>
            <groupId>io.appium</groupId>
            <artifactId>java-client</artifactId>
            <version>${appium.version}</version>
        </dependency>

        <!-- TestNG -->
        <dependency>
            <groupId>org.testng</groupId>
            <artifactId>testng</artifactId>
            <version>${testng.version}</version>
        </dependency>

        <!-- Selenium WebDriver -->
        <dependency>
            <groupId>org.seleniumhq.selenium</groupId>
            <artifactId>selenium-java</artifactId>
            <version>${selenium.version}</version>
        </dependency>

        <!-- Logging -->
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-api</artifactId>
            <version>2.0.7</version>
        </dependency>
        <dependency>
            <groupId>ch.qos.logback</groupId>
            <artifactId>logback-classic</artifactId>
            <version>1.4.11</version>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.11.0</version>
                <configuration>
                    <source>${maven.compiler.source}</source>
                    <target>${maven.compiler.target}</target>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>3.1.2</version>
                <configuration>
                    <suiteXmlFiles>
                        <suiteXmlFile>testng.xml</suiteXmlFile>
                    </suiteXmlFiles>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```

## Step 3: Create Project Structure
Create the following directory structure:
```
src
├── main
│   └── java
│       └── com
│           └── yourcompany
│               ├── config
│               ├── pages
│               └── utils
└── test
    ├── java
    │   └── com
    │       └── yourcompany
    │           └── tests
    └── resources
        └── testng.xml
```

## Step 4: Create Base Configuration
Create `src/main/java/com/yourcompany/config/AppiumConfig.java`:

```java
package com.yourcompany.config;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class AppiumConfig {
    private static final String APPIUM_SERVER = "http://127.0.0.1:4723";
    
    public static IOSDriver getIOSDriver() throws MalformedURLException {
        XCUITestOptions options = new XCUITestOptions()
            .setDeviceName("iPhone 14") // Use your simulator name or device name
            .setUdid("YOUR_DEVICE_UDID") // Optional: Use if you have multiple devices
            .setBundleId("com.example.app") // Your app's bundle ID
            .setAutomationName("XCUITest")
            .setNoReset(true)
            // Additional iOS-specific capabilities
            .setWdaLaunchTimeout(Duration.ofSeconds(30))
            .setWdaConnectionTimeout(Duration.ofSeconds(30))
            .setNewCommandTimeout(Duration.ofSeconds(30));

        return new IOSDriver(new URL(APPIUM_SERVER), options);
    }
}
```

## Step 5: Create Base Test Class
Create `src/test/java/com/yourcompany/tests/BaseTest.java`:

```java
package com.yourcompany.tests;

import com.yourcompany.config.AppiumConfig;
import io.appium.java_client.ios.IOSDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BaseTest {
    protected IOSDriver driver;

    @BeforeMethod
    public void setUp() throws MalformedURLException {
        driver = AppiumConfig.getIOSDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
```

## Step 6: Create Page Object
Create `src/main/java/com/yourcompany/pages/LoginPage.java`:

```java
package com.yourcompany.pages;

import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private final IOSDriver driver;
    private final WebDriverWait wait;

    // Locators - Using iOS-specific locator strategies
    private final By usernameField = By.name("username"); // Using accessibility ID
    private final By passwordField = By.name("password"); // Using accessibility ID
    private final By loginButton = By.name("login"); // Using accessibility ID

    public LoginPage(IOSDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void login(String username, String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField)).sendKeys(username);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();
    }

    // iOS-specific methods
    public void hideKeyboard() {
        driver.hideKeyboard();
    }

    public void scrollToElement(By element) {
        // iOS-specific scroll implementation
        driver.executeScript("mobile: scroll", element);
    }
}
```

## Step 7: Create Test Class
Create `src/test/java/com/yourcompany/tests/LoginTest.java`:

```java
package com.yourcompany.tests;

import com.yourcompany.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
    
    @Test
    public void testSuccessfulLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("testuser", "password123");
        loginPage.hideKeyboard(); // iOS-specific keyboard handling
        
        // Add your assertions here
        // Example: Assert.assertTrue(driver.findElement(By.name("welcome_message")).isDisplayed());
    }

    @Test
    public void testInvalidLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("invalid", "invalid");
        loginPage.hideKeyboard();
        
        // Add assertions for error message
    }
}
```

## Step 8: Create TestNG XML
Create `src/test/resources/testng.xml`:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE suite SYSTEM "https://testng.org/testng-1.0.dtd">
<suite name="iOS App Test Suite">
    <test name="Login Tests">
        <classes>
            <class name="com.yourcompany.tests.LoginTest"/>
        </classes>
    </test>
</suite>
```

## Step 9: Run the Tests
1. Start Appium Server:
   ```bash
   appium
   ```

2. Run tests using Maven:
   ```bash
   mvn clean test
   ```

## iOS-Specific Considerations
1. **WebDriverAgent Setup:**
   - WebDriverAgent is required for iOS testing
   - It comes bundled with Appium
   - May need to be signed with your Apple Developer account
   - Located in: `~/.appium/node_modules/appium-xcuitest-driver/WebDriverAgent`

2. **Device/Simulator Requirements:**
   - iOS 10.0 or higher
   - Xcode 12.0 or higher
   - For physical devices: iOS device with iOS 10.0 or higher

3. **iOS-Specific Capabilities:**
   - `bundleId`: Your app's bundle identifier
   - `udid`: Unique device identifier
   - `automationName`: Must be "XCUITest"
   - `platformVersion`: iOS version
   - `deviceName`: Simulator or device name

## Common Issues and Solutions
1. **WebDriverAgent Issues:**
   - Sign WebDriverAgent with your Apple Developer account
   - Check WebDriverAgent logs in Xcode
   - Verify WebDriverAgent is properly installed

2. **Simulator/Device Issues:**
   - Verify device is trusted in Xcode
   - Check if device is in development mode
   - Ensure correct iOS version is installed

3. **App Installation Issues:**
   - Verify bundle ID is correct
   - Check if app is signed properly
   - Ensure app is built for the correct architecture

## Best Practices
1. **Project Organization:**
   - Use Page Object Model
   - Separate iOS-specific implementations
   - Use iOS-specific locator strategies
   - Implement proper wait strategies for iOS

2. **Test Design:**
   - Handle iOS-specific gestures
   - Implement proper keyboard handling
   - Use iOS-specific assertions
   - Consider iOS version differences

3. **Maintenance:**
   - Keep Xcode and iOS SDK updated
   - Update WebDriverAgent regularly
   - Document iOS-specific test cases
   - Use meaningful test names

## Resources
- [Appium iOS Documentation](http://appium.io/docs/en/writing-running-appium/ios/ios-xctest-mobile-gestures/)
- [XCUITest Documentation](https://developer.apple.com/documentation/xctest)
- [TestNG Documentation](https://testng.org/doc/)
- [Maven Documentation](https://maven.apache.org/guides/)

## Important Notes
- iOS testing requires macOS
- Physical device testing requires Apple Developer account
- Some features may behave differently on simulator vs. real device
- Performance in simulator may differ from real devices
- Certain hardware features are simulated in the simulator
