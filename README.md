# GD Client

Android floating-menu prototype for a Geometry Dash-style offline modding experiment.

## Current status
- Blue floating-menu prototype and Android APK CI build.
- Auto Play, Noclip, Fly, Auto Complete, Practice Auto Complete toggles.
- Speed multiplier slider.
- Gems amount slider plus a separate add button.
- UI settings currently affect the menu only. **Gameplay integration is not implemented yet**; no gameplay, coins, completion records, or currency are changed by this prototype.

## Build
GitHub Actions builds a debug APK on pushes to `main`. Open Actions and download the `gd-client-debug-apk` artifact.

## Requirements
Android SDK 35, JDK 17, Gradle 8.9.
