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
or run the command as bellow to create a simple Java maven project
```cmd
mvn archetype:generate -DgroupId=com.smarttestinglab -DartifactId=source-java-appium-free-source -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
```
## Step 2: Configur the essential dependencies pom.xml
Add the following dependencies to your `pom.xml`:
# Java Cucumber
```xml
<!-- https://mvnrepository.com/artifact/org.testng/testng -->
<dependency>
    <groupId>org.testng</groupId>
    <artifactId>testng</artifactId>
    <version>7.11.0</version>
    <scope>test</scope>
</dependency>
<!-- https://mvnrepository.com/artifact/io.appium/java-client -->
<dependency>
    <groupId>io.appium</groupId>
    <artifactId>java-client</artifactId>
    <version>8.6.0</version>
</dependency>
<!-- https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java -->
<dependency>
    <groupId>org.seleniumhq.selenium</groupId>
    <artifactId>selenium-java</artifactId>
    <version>4.34.0</version>
</dependency>
```

Version Compatibility
Make sure your versions are compatible:

Appium Java Client 8.x works with Selenium 4.x
Appium Java Client 7.x works with Selenium 3.x

Configure Build
```xml
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
    </plugins>
  </build>
```

## Step 3: Create basic test script without TestNG
Create `/src/test/java/com/smarttestinglab/FirstTestAndroidAppium.java`:

### Define the Capability
```java
UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName("Android")
                .setPlatformVersion("11")
                .setAutomationName("UiAutomator2")
                .setDeviceName("Android Emulator")
                .setApp("/Users/doaitran/Documents/Personal/Coding/appium-everything/java-appium-free-course/source-java-appium-free-source/apps/mda-2.2.0-25.apk")
                .setNoReset(false)
                .setAutoGrantPermissions(true)
                .setAppPackage(APP_PACKAGE)
                .setAppWaitActivity(APP_ACTIVITY);
```

### Define a driver

```java
AndroidDriver driver;
driver = new AndroidDriver(new URL(APPIUM_SERVER_URL), options);

```

## Step 4: Create basic test script with TestNG
Create `/src/test/java/com/smarttestinglab/FirstTestAndroidAppiumTestNG.java`:

```java
public class FirstTestAndroidAppiumTestNG {

    private AndroidDriver driver;
    private WebDriverWait wait;

    // App details for SauceLabs MyDemo Android App
    private static final String APP_PACKAGE = "com.saucelabs.mydemoapp.android";
    private static final String APP_ACTIVITY = "com.saucelabs.mydemoapp.android.view.activities.MainActivity";
    private static final String APPIUM_SERVER_URL = "http://127.0.0.1:4723";

    @BeforeMethod
    public void setUp() throws MalformedURLException {
        // Configure UiAutomator2 options for Android
        UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName("Android")
                .setPlatformVersion("11")
                .setAutomationName("UiAutomator2")
                .setDeviceName("Android Emulator")
                .setApp("/Users/doaitran/Documents/Personal/Coding/appium-everything/java-appium-free-course/source-java-appium-free-source/apps/mda-2.2.0-25.apk")
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

    @Test(priority = 1, description = "Verify app launches successfully")
    public void testAppLaunch() {
        System.out.println("Starting testAppLaunch...");

        WebElement appLogoAndName = wait.until(ExpectedConditions.visibilityOfElementLocated(
                AppiumBy.accessibilityId("App logo and name")));
        Assert.assertTrue(appLogoAndName.isDisplayed(), "App Logo should be visible");
        System.out.println("App launched successfully - App Logo is visible");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            try {
                System.out.println("Closing driver...");
                driver.quit();
                System.out.println("Driver closed successfully");
            } catch (Exception e) {
                System.err.println("Error closing driver: " + e.getMessage());
            }
        }
    }
}
```