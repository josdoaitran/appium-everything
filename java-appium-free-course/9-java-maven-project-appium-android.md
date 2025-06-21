# Setting Up Java Maven Project with Appium for Android Testing

## Prerequisites
- Java JDK (version 8 or higher) installed
- Maven installed
- Android Studio and Android SDK installed
- Appium Server installed
- IDE (IntelliJ IDEA or Eclipse) installed
- Android device or emulator set up

## Step 1: Create a New Maven Project
1. Open your IDE (IntelliJ IDEA recommended)
2. Create a new Maven project:
   - Click "File" → "New" → "Project"
   - Select "Maven"
   - Choose "Create from archetype" and select "maven-archetype-quickstart"
   - Fill in the project details:
     ```
     GroupId: com.yourcompany
     ArtifactId: appium-android-test
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
    <artifactId>appium-android-test</artifactId>
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

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class AppiumConfig {
    private static final String APPIUM_SERVER = "http://127.0.0.1:4723";
    
    public static AndroidDriver getAndroidDriver() throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options()
            .setDeviceName("Your_Device_Name") // Use your device name or emulator
            .setUdid("Your_Device_UDID") // Optional: Use if you have multiple devices
            .setAppPackage("com.example.app") // Your app's package name
            .setAppActivity("com.example.app.MainActivity") // Your app's main activity
            .setAutomationName("UiAutomator2")
            .setNoReset(true);

        return new AndroidDriver(new URL(APPIUM_SERVER), options);
    }
}
```

## Step 5: Create Base Test Class
Create `src/test/java/com/yourcompany/tests/BaseTest.java`:

```java
package com.yourcompany.tests;

import com.yourcompany.config.AppiumConfig;
import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    protected AndroidDriver driver;

    @BeforeMethod
    public void setUp() throws MalformedURLException {
        driver = AppiumConfig.getAndroidDriver();
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

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private final AndroidDriver driver;
    private final WebDriverWait wait;

    // Locators
    private final By usernameField = By.id("com.example.app:id/username");
    private final By passwordField = By.id("com.example.app:id/password");
    private final By loginButton = By.id("com.example.app:id/login");

    public LoginPage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void login(String username, String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField)).sendKeys(username);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();
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
        
        // Add your assertions here
        // Example: Assert.assertTrue(driver.findElement(By.id("welcome_message")).isDisplayed());
    }
}
```

## Step 8: Create TestNG XML
Create `src/test/resources/testng.xml`:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE suite SYSTEM "https://testng.org/testng-1.0.dtd">
<suite name="Android App Test Suite">
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

## Common Issues and Solutions
1. **Appium Server Connection Issues:**
   - Verify Appium server is running
   - Check if the port (4723) is available
   - Ensure correct device/emulator is connected

2. **Device/Emulator Issues:**
   - Verify device is connected (`adb devices`)
   - Check if USB debugging is enabled
   - Ensure correct device name in capabilities

3. **App Installation Issues:**
   - Verify app package name and activity
   - Check if app is installed on device
   - Ensure correct app path in capabilities

## Best Practices
1. **Project Organization:**
   - Use Page Object Model
   - Separate test data from test code
   - Use configuration files for different environments

2. **Test Design:**
   - Write independent tests
   - Use proper assertions
   - Implement proper wait strategies
   - Handle exceptions appropriately

3. **Maintenance:**
   - Keep dependencies updated
   - Document test cases
   - Use meaningful test names
   - Implement proper logging

## Resources
- [Appium Documentation](http://appium.io/docs/en/about-appium/intro/)
- [TestNG Documentation](https://testng.org/doc/)
- [Selenium Documentation](https://www.selenium.dev/documentation/)
- [Maven Documentation](https://maven.apache.org/guides/)
