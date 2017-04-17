package za.co.sovtech.rnaac;

import android.os.Environment;
import android.util.Log;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import java.io.File;

import cafe.adriel.androidaudioconverter.AndroidAudioConverter;
import cafe.adriel.androidaudioconverter.callback.IConvertCallback;
import cafe.adriel.androidaudioconverter.callback.ILoadCallback;
import cafe.adriel.androidaudioconverter.model.AudioFormat;

public class ReactNativeAndroidAudioConverterModule extends ReactContextBaseJavaModule {
    public ReactNativeAndroidAudioConverterModule(final ReactApplicationContext reactContext) {
        super(reactContext);
        AndroidAudioConverter.load(reactContext, new ILoadCallback() {
            @Override
            public void onSuccess() {
                Log.d("jono message", "success");
                convertAudioFile(reactContext, "my_audio.flac");
            }

            @Override
            public void onFailure(Exception error) {
                Log.d("jono message", "failure");
                Log.d("jono message", error.toString());
                error.printStackTrace();
                // FFmpeg is not supported by device
            }
        });
    }

    @Override
    public String getName() {
        return "ReactNativeAndroidAudioConverter";
    }

    @ReactMethod
    public void convertAudioFile(ReactApplicationContext reactContext, String fileName) {
        File flacFile = new File(Environment.getExternalStorageDirectory(), fileName);
        Log.d("jono message", Environment.getExternalStorageDirectory().toString());
        IConvertCallback callback = new IConvertCallback() {
            @Override
            public void onSuccess(File convertedFile) {
                // So fast? Love it!
                Log.d("jono message", "convert success");
            }

            @Override
            public void onFailure(Exception error) {
                // Oops! Something went wrong
                Log.d("jono message", "convert failure");
                Log.d("jono message", error.toString());
                error.printStackTrace();
            }
        };
        AndroidAudioConverter.with(reactContext)
                // Your current audio file
                .setFile(flacFile)

                // Your desired audio format
                .setFormat(AudioFormat.MP3)

                // An callback to know when conversion is finished
                .setCallback(callback)

                // Start conversion
                .convert();
    }
}