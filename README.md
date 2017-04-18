# React Native Android Audio Converter

A simple AndroidAudioConverter wrapper for React Native

### How to install?

1. `yarn add reactnativeandroidaudioconverter`

2. link the library either automatically or manually

## Linking AUTOMATICALLY

#### 1. `react native link`

## Linking MANUALLY


#### 1. android/settings.gradle
```
include ':rnaac', ':app'
project(':rnaac').projectDir = new File(rootProject.projectDir, '../../android')

```

#### 2. android/app/build.gradle
```
dependencies {
    compile project(':rnaac')  // <---- add this line
}
```

#### 3. android/app/src/main/java/.../MainApplication.java
```
import za.co.sovtech.rnaac.ReactNativeAndroidAudioConverterPackage;  // <---- add this line

public class MainApplication extends ReactApplication {
    @Override
    protected List<ReactPackage> getPackages() {
        return Arrays.<ReactPackage>asList(
                new MainReactPackage(),
                new ReactNativeAndroidAudioConverterPackage() // <---- add this line
        );
    }
```

#### 4. android/app/src/main/AndroidManifest.xml
```
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
```

### Dependencies

This package uses [AndroidAudioConverter](https://github.com/adrielcafe/AndroidAudioConverter) which in turn uses [FFmpeg-Android-Java](https://github.com/WritingMinds/ffmpeg-android-java)