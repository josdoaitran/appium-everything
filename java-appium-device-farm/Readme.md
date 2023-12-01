# AWS Device Farm Note:
- AWS Device Farm: https://docs.aws.amazon.com/devicefarm/latest/developerguide/welcome.html
- Device Farm is only available in the us-west-2 (Oregon) region.
# Projects steps:
- Build to artifacts
- Create project
- Create test run
- Upload the build testing (apk, ipa files)
or we can automate it https://docs.aws.amazon.com/devicefarm/latest/developerguide/api-ref.html
- Upload testing artifacts.
- Trigger run.

# Build the artifact for Java:
Following these below steps:
##  Build plugin configuration:
Assure your project is setup correct build plugin configuration:

```agsl
<build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-jar-plugin</artifactId>
                <version>2.6</version>
                <executions>
                    <execution>
                        <goals>
                            <goal>test-jar</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-dependency-plugin</artifactId>
                <version>2.10</version>
                <executions>
                    <execution>
                        <id>copy-dependencies</id>
                        <phase>package</phase>
                        <goals>
                            <goal>copy-dependencies</goal>
                        </goals>
                        <configuration>
                            <outputDirectory>${project.build.directory}/dependency-jars/</outputDirectory>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
            <plugin>
                <artifactId>maven-assembly-plugin</artifactId>
                <version>2.5.4</version>
                <executions>
                    <execution>
                        <phase>package</phase>
                        <goals>
                            <goal>single</goal>
                        </goals>
                        <configuration>
                            <finalName>zip-with-dependencies</finalName>
                            <appendAssemblyId>false</appendAssemblyId>
                            <descriptors>
                                <descriptor>src/main/assembly/zip.xml</descriptor>
                            </descriptors>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>
```

## Update Assembly to zip configuration:
Create the zip.xml file on `src/main/assembly/zip.xml`.

```agsl
<assembly
    xmlns="http://maven.apache.org/plugins/maven-assembly-plugin/assembly/1.1.0"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://maven.apache.org/plugins/maven-assembly-plugin/assembly/1.1.0 http://maven.apache.org/xsd/assembly-1.1.0.xsd">
  <id>zip</id>
  <formats>
    <format>zip</format>
  </formats>
  <includeBaseDirectory>false</includeBaseDirectory>
  <fileSets>
    <fileSet>
      <directory>${project.build.directory}</directory>
      <outputDirectory>./</outputDirectory>
      <includes>
        <include>*.jar</include>
      </includes>
    </fileSet>
    <fileSet>
      <directory>${project.build.directory}</directory>
      <outputDirectory>./</outputDirectory>
      <includes>
        <include>/dependency-jars/</include>
      </includes>
    </fileSet>
  </fileSets>
</assembly>
```

## Build package our testing:
Build and package your tests by run this comment:
`$ mvn clean package -DskipTests=true`

# Example app - Using in this example project:
https://github.com/saucelabs/sample-app-mobile


# References:
- AWS Device Farm: https://docs.aws.amazon.com/devicefarm/latest/developerguide/test-types-appium.html
- AWS Device Farm permission: https://docs.aws.amazon.com/devicefarm/latest/developerguide/security_iam_service-with-iam.html
- Java Appium - Device Farm Example: https://github.com/aws-samples/aws-device-farm-appium-tests-for-sample-app
- Aws-device-farm-appium-cucumber-tests-for-sample-app: https://github.com/aws-samples/aws-device-farm-appium-cucumber-tests-for-sample-app
- Device Farm example - Calabash test: https://github.com/aws-samples/aws-device-farm-sample-app-for-ios
- Appium 1 to 2: https://appium.io/docs/en/2.1/guides/migrating-1-to-2/
- AWS Device Farm permission: https://aws.permissions.cloud/iam/devicefarm