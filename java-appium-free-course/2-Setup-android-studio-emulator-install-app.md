# Setting Up Android Studio Emulator and Installing Apps

## Prerequisites
- Android Studio installed
- Java Development Kit (JDK) installed
- Sufficient disk space (at least 10GB recommended)
- Virtualization enabled in BIOS (for Intel/AMD processors)

## Step 1: Launch Android Studio
1. Open Android Studio
2. Click on "More Actions" or "Configure" (if on welcome screen)
3. Select "Virtual Device Manager" or "AVD Manager"

## Step 2: Create a New Virtual Device
1. Click "Create Virtual Device" button
2. Select a device definition:
   - Choose a phone (e.g., Pixel 2)
   - Select screen size and resolution
   - Click "Next"
3. Select system image:
   - Choose a recommended Android version (e.g., API 33 - Android 13.0)
   - If not downloaded, click "Download" next to the system image
   - Click "Next"
4. Configure AVD:
   - Name your virtual device
   - Adjust startup orientation (Portrait/Landscape)
   - Other settings can remain default
   - Click "Finish"

## Step 3: Start the Emulator
1. In AVD Manager, find your created device
2. Click the play button (▶️) next to your device
3. Wait for the emulator to boot up (first boot may take several minutes)

## Step 4: Install Apps on Emulator
### Method 1: Using Android Studio
1. Open your Android project in Android Studio
2. Click "Run" (green play button) or press Shift + F10
3. Select your running emulator
4. Wait for the app to build and install

### Method 2: Using ADB (Android Debug Bridge)
1. Open terminal/command prompt
2. Navigate to the directory containing your .apk file
3. Run the following command:
   ```bash
   adb install your-app.apk
   ```

### Method 3: Drag and Drop
1. Locate your .apk file on your computer
2. Drag and drop the .apk file onto the running emulator
3. The app will automatically install

## Step 5: Verify Installation
1. On the emulator, go to the app drawer
2. Look for your installed app
3. Launch the app to verify it works correctly

## Troubleshooting Common Issues
1. **Emulator is slow:**
   - Enable hardware acceleration in BIOS
   - Allocate more RAM to the emulator
   - Use a lower API level

2. **App installation fails:**
   - Check if the .apk is compatible with the emulator's Android version
   - Verify you have enough storage space
   - Check if the .apk is corrupted

3. **Emulator won't start:**
   - Verify virtualization is enabled in BIOS
   - Check system requirements
   - Try cold booting the emulator

## Additional Tips
- Keep Android Studio and SDK tools updated
- Use x86 system images for better performance
- Consider using a physical device for testing if emulator performance is poor
- Use the emulator's extended controls for additional features (camera, GPS, etc.)

## Resources
- [Official Android Studio Documentation](https://developer.android.com/studio)
- [Android Emulator Documentation](https://developer.android.com/studio/run/emulator)
- [ADB Documentation](https://developer.android.com/studio/command-line/adb)
