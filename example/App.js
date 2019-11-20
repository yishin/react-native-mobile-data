/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow
 */

import React, {Component} from 'react';
import {StyleSheet, Text, View, Button} from 'react-native';
import RNMobileData from 'react-native-mobile-data';

export default class App extends Component {
  constructor(props) {
    super(props);

    this.state = {
      loading: false,
    };
  }

  componentDidMount() {
    //
  }

  componentWillUnmount() {
    //
  }

  render() {
    return (
      <View style={styles.container}>
        <View style={{ flexDirection: 'row', alignSelf: 'stretch', justifyContent: 'space-around' }}>
          <Button
            title='Enable'
            onPress={() => RNMobileData.setMobileDataEnable(true)}
            color='blue'
          />
          <Button
            title='Disable'
            onPress={() => RNMobileData.setMobileDataEnable(false)}
            color='red'
          />
        </View>
        <Text style={{ color: 'black', fontSize: 16 }}>{this.state.playbackState}</Text>
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
