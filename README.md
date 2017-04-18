# React Native Android Audio Converter

A simple AndroidAudioConverter wrapper for React Native

## How to install?

1. `yarn add reactnativeandroidaudioconverter`

2. link the library either automatically or manually

## Linking AUTOMATICALLY

#### 1. `react native link`

## Linking MANUALLY


#### 1. android/settings.gradle
```
include ':react-native-android-audio-converter' // <---- add this line
project(':react-native-android-audio-converter').projectDir = new File(rootProject.projectDir, '../node_modules/react-native-android-audio-converter/android') // <---- add this line

```

#### 2. android/app/build.gradle
```
dependencies {
    compile project(':react-native-android-audio-converter') // <---- add this line
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

## How to use it?

#### 1. Import the package

`import {AudioConverter} from 'react-native-android-audio-converter';`

#### 2. Make sure AndroidAudioConverter has initialized

a message will appear in the android logs

#### 3. Call AudioConverter.convertAudioFile

```
AudioConverter.convertAudioFile("my_audio.flac", (successMessage) => {
                console.log(successMessage);
            }, (errorMessage) => {
                console.log(errorMessage);
            });
```

## Dependencies

This package uses [AndroidAudioConverter](https://github.com/adrielcafe/AndroidAudioConverter) which in turn uses [FFmpeg-Android-Java](https://github.com/WritingMinds/ffmpeg-android-java)