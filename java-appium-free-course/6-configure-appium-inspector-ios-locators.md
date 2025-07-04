# Steps to setup Appium Server and Appium Inspector
- Install XCUITest Driver
- Start Appium in MacOSX
- Setup Capability to inspect locator

## Command to install XCUITest

```
appium driver list --installed
appium driver install xcuitest
```

After, installed successfully. You expect to receive this result to check you installed successfully or not.

```
appium driver list
   ✔ Listing available drivers
   - uiautomator2@4.2.3 [installed (npm)]
   - xcuitest [installed (npm)]
```

## Start Appium Server
- Open Termninal in your Mac Machine
- Run this command:
```
appium
```

## Setup Capability to inspect locator
Fill in the following capabilities in Appium Inspector:

### Required Capabilities:
```json
{
  "platformName": "iOS",
  "appium:automationName": "XCUITest",
  "appium:appPackage": "com.saucelabs.mydemoapp.ios",
  "appium:app": "/Users/doaitran/Documents/Personal/Smart Testing Lab/mda-2.2.0-25.apk",
  "appium:noReset": true
}
```

# Some common errors and solutions

## Error - Missing Appium Driver XCUITest
If you miss install XCUITest Driver for your Appium Server, you will receive the error as bellow
![](./images/appium-inspector-error-missing-xcuitest.png)