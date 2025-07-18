# Setting Up Java Maven Project with Appium for iOS Testing

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
     ArtifactId: appium-test
     Version: 1.0-SNAPSHOT
     ```
or run the command as bellow to create a simple Java maven project
```cmd
mvn archetype:generate -DgroupId=com.smarttestinglab -DartifactId=source-java-appium-free-source -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
```
## Step 2: Configur the essential dependencies pom.xml
Add the following dependencies to your `pom.xml`:

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
Create `/src/test/java/com/smarttestinglab/lesson7/FirstTestAndroidAppium.java`:

### Define the Capability
```java
XCUITestOptions options = new XCUITestOptions()
                .setPlatformName("iOS")
                .setPlatformVersion("18.5")
                .setDeviceName("iPhone 16 Pro")
                .setAutomationName("XCUITest")
                .setApp("/Users/doaitran/Documents/Personal/Smart Testing Lab/source-java-appium-free-source/build/my_demo_app.app")
                .setNoReset(false);
```

### Define a driver

```java
IOSDriver driver = new IOSDriver(new URL("http://127.0.0.1:4723"), options);

```

## Step 4: Create basic test script with TestNG
Create `/src/test/java/com/smarttestinglab/lesson9/FirstTestIosAppiumTestNG.java`:

```java
public class FirstTestIosAppiumTestNG {
    private IOSDriver driver;
    public static final String APP_DIRECTORY = "build/my_demo_app.app";

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
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        System.out.println("Test Setup completed successfully");
        WebElement appLogoName = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("AppTitle Icons")));
        Assert.assertTrue(appLogoName.isDisplayed());
    }

    @Test(priority = 1)
    public void testAppProductList() {
        System.out.println("Test App Product List");
        WebElement appProductList = driver.findElement(AppiumBy.accessibilityId("title"));
        Assert.assertTrue(appProductList.isDisplayed());
        System.out.println("App Product List is visible");
    }

    @Test(priority = 2)
    public void testAppMenu(){
        System.out.println("Test App Menu");
        System.out.println("Touch on App Menu");
        WebElement appMenuIcon = driver.findElement(AppiumBy.accessibilityId("More-tab-item"));
        appMenuIcon.click();
        System.out.println("Verify App Menu");
        WebElement appMenuLogin = driver.findElement(AppiumBy.accessibilityId("LogOut-menu-item"));
        Assert.assertTrue(appMenuLogin.isDisplayed());
        System.out.println("App Menu Login is visible");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
        System.out.println("Driver closed successfully");
    }
}
```
