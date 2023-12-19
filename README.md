# Appium-note-everything
- Appium: https://appium.io/docs/en/2.1/
- Appium 1 to 2: https://appium.io/docs/en/2.1/guides/migrating-1-to-2/

# Basic Appium:
- Installation: https://appium.io/docs/en/2.1/quickstart/
- Apium Inspector: https://github.com/appium/appium-inspector
- Session Capability: https://appium.io/docs/en/2.3/guides/caps/

## Installation Appium (Windows, Mac)
In these steps, we can apply for almost platforms (Windows, MacOSX, Ubuntu, ...)
- Install npm from nodejs
`https://nodejs.org/en/download`
- Install Appium 2.0 official version by running this command
`npm i --location=global appium`
- Appium for Android by running this command
`appium driver install uiautomator2 `
- Appium for ios by running this command
`appium driver install xcuitest@4.12.2`

## Start Appium
### On Windows
we can run this command: `appium ` on your command line.
### On MacOSX
we should the below steps to ensure all configurations are set up properly.
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


## Note: Uninstall Appium 1.x and Upgrade to Version 2.x
Refer to this note: https://github.com/josdoaitran/appium-everything/blob/master/install_appium_install_again_appium.md

# Example and Reference Project:
- Appium Java with Device Farm: https://github.com/josdoaitran/appium-everything/tree/main/java-appium-device-farm

# Appium with Visual Testing:
- Applitools: [https://applitools.com/](https://applitools.com/tutorials/quickstart/native-mobile/appium/java)
- Percy mobile browser testing: https://www.browserstack.com/docs/percy/integrate/appium-mbt
- Kobiton Visual Testing: https://kobiton.com/capabilities/visual-testing/
- Testfigma: https://testsigma.com/visual-automation-testing

# References:
- https://medium.com/tauk-blog/quick-start-guide-for-setting-up-appium-on-an-m1-mac-a629a70a40cb
