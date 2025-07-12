# Maven Commands to Create a Maven Project

Here are the Maven commands to create a new Maven project:

## Basic Maven Project Creation

### 1. **Simple Maven Project (Quickstart)**
```bash
mvn archetype:generate -DgroupId=com.example -DartifactId=my-app -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
```

### 2. **Interactive Mode (Prompts for input)**
```bash
mvn archetype:generate
```

### 3. **Specific Maven Version**
```bash
mvn archetype:generate -DgroupId=com.example -DartifactId=my-app -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.4 -DinteractiveMode=false
```

## Appium-Specific Maven Project

### **For Appium Testing Project:**
```bash
mvn archetype:generate \
  -DgroupId=com.yourcompany.appium \
  -DartifactId=appium-test-project \
  -DarchetypeArtifactId=maven-archetype-quickstart \
  -DarchetypeVersion=1.4 \
  -DinteractiveMode=false
```

## Command Parameters Explained

- **`-DgroupId`**: Your project's group ID (e.g., `com.yourcompany.appium`)
- **`-DartifactId`**: Your project name (e.g., `appium-test-project`)
- **`-DarchetypeArtifactId`**: Template to use (`maven-archetype-quickstart` for basic Java project)
- **`-DarchetypeVersion`**: Version of the archetype (optional)
- **`-DinteractiveMode=false`**: Skip interactive prompts

## Common Archetypes

### 1. **Basic Java Project**
```bash
mvn archetype:generate -DarchetypeArtifactId=maven-archetype-quickstart
```

### 2. **Web Application**
```bash
mvn archetype:generate -DarchetypeArtifactId=maven-archetype-webapp
```

### 3. **Simple Project (No src/test)**
```bash
mvn archetype:generate -DarchetypeArtifactId=maven-archetype-simple
```

## Example: Complete Appium Project Creation

```bash
# Create the project
mvn archetype:generate \
  -DgroupId=com.automation.appium \
  -DartifactId=mobile-test-automation \
  -DarchetypeArtifactId=maven-archetype-quickstart \
  -DarchetypeVersion=1.4 \
  -DinteractiveMode=false

# Navigate to project directory
cd mobile-test-automation

# Verify project structure
ls -la
```

## Expected Project Structure

After running the command, you'll get:
```
mobile-test-automation/
├── pom.xml
└── src/
    ├── main/
    │   └── java/
    │       └── com/
    │           └── automation/
    │               └── appium/
    │                   └── App.java
    └── test/
        └── java/
            └── com/
                └── automation/
                    └── appium/
                        └── AppTest.java
```

## Next Steps After Project Creation

1. **Navigate to project directory:**
   ```bash
   cd your-project-name
   ```

2. **Update `pom.xml` with Appium dependencies:**
   ```bash
   # Edit pom.xml to add Appium, TestNG, etc.
   ```

3. **Compile the project:**
   ```bash
   mvn compile
   ```

4. **Run tests:**
   ```bash
   mvn test
   ```

## Quick Project Creation Script

You can also create a simple script:

```bash
#!/bin/bash
# create-appium-project.sh

PROJECT_NAME=$1
GROUP_ID="com.automation.appium"

if [ -z "$PROJECT_NAME" ]; then
    echo "Usage: ./create-appium-project.sh <project-name>"
    exit 1
fi

mvn archetype:generate \
  -DgroupId=$GROUP_ID \
  -DartifactId=$PROJECT_NAME \
  -DarchetypeArtifactId=maven-archetype-quickstart \
  -DarchetypeVersion=1.4 \
  -DinteractiveMode=false

echo "Project $PROJECT_NAME created successfully!"
echo "Next steps:"
echo "1. cd $PROJECT_NAME"
echo "2. Update pom.xml with Appium dependencies"
echo "3. mvn compile"
```

Use it like:
```bash
chmod +x create-appium-project.sh
./create-appium-project.sh my-appium-tests
```

## Sample pom.xml for Appium Project

After creating the project, update your `pom.xml` with Appium dependencies:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    
    <groupId>com.automation.appium</groupId>
    <artifactId>mobile-test-automation</artifactId>
    <version>1.0-SNAPSHOT</version>
    
    <properties>
        <maven.compiler.source>17</maven.compiler.source>
        <maven.compiler.target>17</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <appium.version>9.3.0</appium.version>
        <testng.version>7.8.0</testng.version>
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
            <version>4.15.0</version>
        </dependency>
        
        <!-- WebDriverManager -->
        <dependency>
            <groupId>io.github.bonigarcia</groupId>
            <artifactId>webdrivermanager</artifactId>
            <version>5.6.2</version>
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

## Common Maven Commands for Project Management

```bash
# Clean the project
mvn clean

# Compile the project
mvn compile

# Run tests
mvn test

# Package the project
mvn package

# Install to local repository
mvn install

# Clean and compile
mvn clean compile

# Clean and test
mvn clean test

# Skip tests during build
mvn clean package -DskipTests

# Run specific test class
mvn test -Dtest=LoginTest

# Run tests with specific profile
mvn test -Pintegration-tests
```

## Troubleshooting

### Common Issues:

1. **Maven not found:**
   ```bash
   # Check if Maven is installed
   mvn --version
   
   # Install Maven if needed (macOS)
   brew install maven
   
   # Install Maven if needed (Windows)
   # Download from https://maven.apache.org/download.cgi
   ```

2. **Java version issues:**
   ```bash
   # Check Java version
   java -version
   
   # Set JAVA_HOME if needed
   export JAVA_HOME=/path/to/your/java
   ```

3. **Permission issues:**
   ```bash
   # Make sure you have write permissions in the directory
   chmod 755 .
   ```

This will create a clean Maven project structure ready for Appium test development!
