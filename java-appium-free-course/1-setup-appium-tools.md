# Installation list

1. Java + IntelliJ
2. NodeJS
3. Appium
4. Appium Inspector

## Install Java environment and Editor Tools
- Suggestion: Using Java 17+ version
- Download Java via: [https://www.oracle.com/java/technologies/java-se-glance.html](https://www.oracle.com/java/technologies/java-se-glance.html)
- Configure Java Environment Variable
In Mac
```
/usr/libexec/java_home -V
export JAVA_HOME=`/usr/libexec/java_home -v 17.0.10`
```
In Windows, We can configure the Java System Variable.
- Check Java in your machine via Terminal or Powershell, exaple:
```
java -version
java version "17.0.10" 2024-01-16 LTS
Java(TM) SE Runtime Environment (build 17.0.10+11-LTS-240)
Java HotSpot(TM) 64-Bit Server VM (build 17.0.10+11-LTS-240, mixed mode, sharing)
```
- IntelliJ Community tool as Java EDitor tool [https://www.jetbrains.com/idea/download///?section=windows](https://www.jetbrains.com/idea/download///?section=windows)

## Intall NodeJS
- Install npm from nodejs via this [link](https://nodejs.org/en/download)
- Check NodeJS in your machine via Terminal or Powershell, exaple:
```
❯ node --version
v22.15.0
❯ npm --version
10.9.2
```
## Setup and Installation Appium
In these steps, we can apply for almost platforms (Windows, MacOSX, Ubuntu, ...)
- Install Appium 2.0 official version by running this command
`npm i --location=global appium`
- Appium for Android by running this command
`appium driver install uiautomator2 `
- Appium for ios by running this command
`appium driver install xcuitest`

## Start Appium Server
We have 2 main ways to start Appium Server
- Start Appium via open Appium App directly
- Start Appium via Commandline
### Start Appium via Cmd in Windows
we can run this command: `appium ` on your command line.
### Start Appium via Cmd in MacOSX
- Start Appium with this command: `appium`
- More options: `appium server --allow-insecure chromedriver_autodownload --allow-cors --base-path=/wd/hub` to enable chromedriver for your testing browser view in mobile, allow insecure connection and base path for connections clients.

# Install Appium Inspector
- Appium Inspector is as an assistant tools for us to inspect locator of mobile elements
- Appium Inspector is released in 3 formats:
Format 1: Standalone desktop application for macOS, Windows, and Linux - download it from the [Releases](https://github.com/appium/appium-inspector/releases) section
Format 2: Web application - (https://inspector.appiumpro.com)[https://inspector.appiumpro.com] (note that CORS must be enabled in order to connect to an Appium server)
Format 3: Appium server plugin - see the plugin README for details