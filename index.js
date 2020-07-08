/**
 * @format
 */

import {
  AppRegistry
} from 'react-native'
import DeviceInfo from 'react-native-device-info'
import TestApp from './App/testApp'
import ProdApp from './App/prodApp'
import {
  name as appName
} from './app.json'

const App = DeviceInfo.getBundleId().match('nativetesting') ? TestApp : ProdApp

AppRegistry.registerComponent(appName, () => App)