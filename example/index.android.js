/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 * @flow
 */

import React, {Component} from 'react';
import {
    AppRegistry,
    StyleSheet,
    Text,
    View
} from 'react-native';

import AudioConverter from 'react-native-android-audio-converter';

export default class example extends Component {
    render() {

        async function returnFilePath() {
            try {
                let answer = await AudioConverter.getFilePath();
                console.log(answer);
            } catch (e) {
                console.error(e);
            }
        }

        returnFilePath();

        setTimeout(function () {
            AudioConverter.convertAudioFile("my_audio.flac", (successMessage) => {
                console.log(successMessage);
            }, (errorMessage) => {
                console.log(errorMessage);
            });
        }, 5000);

        return (
            <View style={styles.container}>
                <Text style={styles.welcome}>
                    Welcome to React Native!
                </Text>
                <Text style={styles.instructions}>
                    To get started, edit index.android.js
                </Text>
                <Text style={styles.instructions}>
                    Double tap R on your keyboard to reload,{'\n'}
                    Shake or press menu button for dev menu
                </Text>
            </View>
        );
    }
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        justifyContent: 'center',
        alignItems: 'center',
        backgroundColor: '#F5FCFF',
    },
    welcome: {
        fontSize: 20,
        textAlign: 'center',
        margin: 10,
    },
    instructions: {
        textAlign: 'center',
        color: '#333333',
        marginBottom: 5,
    },
});

AppRegistry.registerComponent('example', () => example);
