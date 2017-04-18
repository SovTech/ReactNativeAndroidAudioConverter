package za.co.sovtech.rnaac;

import android.os.Environment;
import android.util.Log;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.uimanager.IllegalViewOperationException;

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
                Log.d("rnaac notice", "successfully loaded AndroidAudioConverter");
            }

            @Override
            public void onFailure(Exception error) {
                // FFmpeg is not supported by device
                Log.d("rnaac notice", error.toString());
                error.printStackTrace();
            }
        });
    }

    /**
     * Function to return the name of the native module
     *
     * @return - native module name
     */
    @Override
    public String getName() {
        return "ReactNativeAndroidAudioConverter";
    }

    /**
     * Function to return the file path of the folder where the converter will look for files
     *
     * @param promise - promise to return file path
     */
    @ReactMethod
    public void getFilePath(Promise promise) {
        try {
            promise.resolve(Environment.getExternalStorageDirectory().toString());
        } catch (IllegalViewOperationException e) {
            promise.reject(e);
        }
    }

    /**
     * Function to convert a given audio file to MP3 format
     *
     * @param fileName        - input file name
     * @param errorCallback   - function called if the file could not be converted
     * @param successCallback - function called if the conversion was successful
     */
    @ReactMethod
    public void convertAudioFile(String fileName,
                                 final Callback errorCallback,
                                 final Callback successCallback) {
        File flacFile = new File(Environment.getExternalStorageDirectory(), fileName);
        Log.d("Output file location", Environment.getExternalStorageDirectory().toString() + fileName);


        IConvertCallback callback = new IConvertCallback() {
            @Override
            public void onSuccess(File convertedFile) {
                // So fast? Love it!
                Log.d("rnaac notice", "convert success");
                successCallback.invoke("convert success");
            }

            @Override
            public void onFailure(Exception error) {
                // Oops! Something went wrong
                errorCallback.invoke("convert failure: " + error.toString());
                Log.d("rnaac notice", error.toString());
                error.printStackTrace();
            }
        };
        AndroidAudioConverter.with(this.getReactApplicationContext())
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