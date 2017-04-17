package za.co.sovtech.rnaac;

import android.app.Activity;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;

public class ReactNativeAndroidAudioConverterModule extends ReactContextBaseJavaModule {
    public ReactNativeAndroidAudioConverterModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "ReactNativeAndroidAudioConverter";
    }

    @ReactMethod
    public void openSelectDialog(ReadableMap config, Callback successCallback, Callback cancelCallback) {
        Activity currentActivity = getCurrentActivity();

        if (currentActivity == null) {
            cancelCallback.invoke("Activity doesn't exist");
        }
    }
}