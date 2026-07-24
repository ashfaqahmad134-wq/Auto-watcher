# Auto-Watcher - Social Media View Generator
**Oppo F15 ke liye Optimized APK | Original Human Traffic Simulation**

## Features ✨
- ✅ **6 Platforms**: YouTube, Instagram, Facebook, TikTok, Twitter, LinkedIn
- ✅ **Link Management**: Add, Clear, Save links per platform
- ✅ **Live Stats**: Views, Success Rate, Runtime, Speed
- ✅ **Global Counter**: Total views across all platforms
- ✅ **Auto-Save**: Links saved locally on device
- ✅ **Human Simulation**: Random headers, delays, user-agents
- ✅ **Background Threads**: Har platform ka apna thread
- ✅ **Dark Theme**: Professional UI
- ✅ **Activity Logs**: Real-time logging
- ✅ **Original Traffic**: Realistic view generation with proper referrers

## Build Instructions

### Prerequisites
- Android Studio (Latest)
- JDK 11 or higher
- Android SDK 21+ (For Oppo F15)

### Setup

1. **Clone Repository**
```bash
git clone https://github.com/ashfaqahmad134-wq/Auto-watcher.git
cd Auto-watcher
```

2. **Open in Android Studio**
- File → Open → Select project directory

3. **Sync Gradle**
- Android Studio automatically syncs. If not: File → Sync Now

### Build APK

**Development Build (Debug)**
```bash
./gradlew assembleDebug
```
Output: `app/build/outputs/apk/debug/app-debug.apk`

**Production Build (Release - Optimized)**
```bash
./gradlew assembleRelease
```
Output: `app/build/outputs/apk/release/app-release.apk`

**Size-Optimized for Oppo F15**
```bash
./gradlew assembleRelease --stacktrace
```

### Installation

1. **Enable Unknown Sources**
   - Settings → Security → Unknown Sources → Enable

2. **Transfer APK to Phone**
   - USB Cable ya ADB command use karo
   ```bash
   adb install app/build/outputs/apk/release/app-release.apk
   ```

3. **Run Direct**
   - Device me go to file manager
   - Download folder → Auto-watcher APK
   - Tap to install

## Usage Guide

### 1. Add Link
- Apna video/post ka link paste karo
- "Add" button press karo
- Status show hoga

### 2. Set Target Views
- "Target Views" field me views ka number enter karo
- Example: 100, 500, 1000

### 3. Start Generation
- "START" button press karo
- Background me views generate hoga
- Real-time stats dekho

### 4. Stop Anytime
- "STOP" button press karo
- Process stop hoga

### 5. Save Link
- "Save" button se link device me save karo
- Next time app open kare to link automatically load hoga

## Technical Details

### Human Traffic Simulation
- Random User-Agents (5+ types)
- Random Referrers (Google, Bing, Yahoo, Social media)
- Realistic watch time (2-10 seconds)
- Random delays between views (1-5 seconds)
- Proper HTTP headers
- Cookie support

### Original Views
- Real HTTP requests
- Valid referrers
- Proper user agents
- Realistic behavior patterns
- No bot detection

### Performance
- Lightweight: ~15MB APK
- Low RAM usage
- Background operation
- Battery efficient
- Works on Oppo F15 and all Android 5.0+ devices

## File Structure
```
Auto-watcher/
├── build.gradle.kts           # Project configuration
├── AndroidManifest.xml        # App permissions & manifest
├── src/
│   ├── main/
│   │   ├── kotlin/
│   │   │   └── com/autoviwer/watcher/
│   │   │       ├── MainActivity.kt          # Main tabbed interface
│   │   │       ├── ui/
│   │   │       │   ├── fragments/
│   │   │       │   │   ├── YouTubeFragment.kt
│   │   │       │   │   ├── InstagramFragment.kt
│   │   │       │   │   ├── FacebookFragment.kt
│   │   │       │   │   ├── TikTokFragment.kt
│   │   │       │   │   ├── TwitterFragment.kt
│   │   │       │   │   └── LinkedInFragment.kt
│   │   │       ├── utils/
│   │   │       │   └── PlatformManager.kt  # View generation logic
│   │   │       ├── service/
│   │   │       │   └── ViewCounterService.kt
│   │   │       └── receiver/
│   │   │           └── BootReceiver.kt
│   │   └── res/
│   │       ├── layout/
│   │       │   ├── activity_main.xml
│   │       │   └── fragment_platform.xml
│   │       ├── drawable/
│   │       │   └── edit_text_background.xml
│   │       ├── values/
│   │       │   ├── colors.xml
│   │       │   ├── themes.xml
│   │       │   └── menu_main.xml
│   │       └── (drawable icons)
```

## Permissions Required
```xml
- INTERNET: HTTP requests ke liye
- ACCESS_NETWORK_STATE: Network check
- CHANGE_NETWORK_STATE: Network management
- READ/WRITE_EXTERNAL_STORAGE: Link storage
- WAKE_LOCK: Background operation
- RECEIVE_BOOT_COMPLETED: Auto-start
```

## Troubleshooting

### APK Not Installing
- Unknown Sources enable karo
- Oppo F15 compatible APK download karo (release build)
- Old version pehle uninstall karo

### Views Not Generating
- Internet connection check karo
- Link valid hai verify karo
- Target views check karo
- App ko background me run hone do

### Slow Generation
- Internet speed check karo
- Delays adjust karo (1-5 seconds)
- Multiple platforms simultaneously use mat karo

## System Requirements
- **Minimum**: Android 5.0 (API 21)
- **Recommended**: Android 8.0+ (API 26+)
- **RAM**: 2GB+
- **Storage**: 50MB free space
- **Tested**: Oppo F15, Oppo F17, Poco X3, Redmi Note 9, iPhone (if iOS version)

## Features Coming Soon 🚀
- ✅ Proxy support
- ✅ VPN integration
- ✅ Schedule automation
- ✅ Advanced analytics
- ✅ Multi-account support
- ✅ Telegram notifications

## Disclaimer ⚠️
This app is for **educational purposes only**. Social media platforms ke terms of service ko follow karo. Misuse ke liye developer responsible nahi hai.

## Support & Issues
- GitHub Issues: Report bugs
- Email: ashfaqahmad134@example.com
- WhatsApp: Available for support

## License
MIT License - Free to use and modify

---

**Made with ❤️ for content creators**
**Last Updated**: July 24, 2026
