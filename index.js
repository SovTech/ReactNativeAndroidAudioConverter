import {NativeModules} from 'react-native';

let AudioConverter = NativeModules.ReactNativeAndroidAudioConverter;

AudioConverter.prototype.convertAudioFile = function (filename, sucCallback, errCallback) {
    AudioConverter.convertAudioFile(filename, (data) => sucCallback(data), (error) => errCallback(error))
};
AudioConverter.prototype.getFilePath = function () {
    AudioConverter.getFilePath()
};

module.exports = AudioConverter;
