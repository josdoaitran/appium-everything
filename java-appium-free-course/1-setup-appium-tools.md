# Installation list

1. Java + IntelliJ
2. NodeJS
3. Appium
4. Appium Inspector

## Install Java environment and Editor Tools
- Suggestion: Using Java 17+ version
- Download Java via: [https://www.oracle.com/java/technologies/java-se-glance.html](https://www.oracle.com/java/technologies/java-se-glance.html)

## Intall NodeJS
- Install npm from nodejs
`https://nodejs.org/en/download`

## Setup and Installation Appium
In these steps, we can apply for almost platforms (Windows, MacOSX, Ubuntu, ...)
- Install Appium 2.0 official version by running this command
`npm i --location=global appium`
- Appium for Android by running this command
`appium driver install uiautomator2 `
- Appium for ios by running this command
`appium driver install xcuitest@4.12.2`

## Start Appium Server

### On Windows
we can run this command: `appium ` on your command line.
### On MacOSX
Note check Java version is already installed on your mac:
```
/usr/libexec/java_home -V
```
we should the below steps to ensure all configurations are set up properly.
- Configure `~/.zprofile`. Suggest to use `zprofile` instead of using `~/.bash_profile`
  Open file .zshenv to edit system environment variable
  
```
JAVA_HOME=`/usr/libexec/java_home -v {{java_version}}`
ANDROID_HOME=$HOME/Library/Android/sdk
PATH=$ANDROID_HOME/tools:$PATH
PATH=$ANDROID_HOME/platform-tools:$PATH
PATH=/opt/apache-maven-3.8.6/bin:$PATH
PATH=$GEM_HOME/bin:$PATH
GEM_HOME=$HOME/.gem
PATH=/usr/local/bin/aws-profile:$PATH
PATH=/usr/local/bin/aws-ssm:$PATH
```
- Configurate `~/.bash_profile` properly for Java_Home, Android_Home
```
JAVA_HOME=$(/usr/libexec/java_home -v {{java_version}}
PATH=$JAVA_HOME/bin:$PATH
ANDROID_HOME=$HOME/Library/Android/sdk
ANDROID_SDK_ROOT=$HOME/Android/Sdk
PATH=$ANDROID_HOME/tools/bin:$PATH
PATH=$ANDROID_HOME/tools:$PATH
PATH=$ANDROID_HOME/platform-tools:$PATH
PATH=$ANDROID_HOME/build-tools/{{version}}:$PATH
PATH="${PATH}:$ANDROID_HOME/emulator:$ANDROID_HOME/tools:$ANDROID_HOME/tools/bin:$ANDROID_HOME/platform-tools"
PATH=/usr/local/bin:/usr/bin:/bin:/usr/sbin:/sbin:$PATH
```
- Start Appium with this command: `appium`

- More options: `appium server --allow-insecure chromedriver_autodownload --allow-cors --base-path=/wd/hub` to enable chromedriver for your testing browser view in mobile, allow insecure connection and base path for connections clients.

# Install Appium Inspector
