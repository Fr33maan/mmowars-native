import React, { useState } from 'react'
import { SafeAreaView, Text, StatusBar, Button } from 'react-native'
import * as modules from './modules'

export function App(): React.ReactElement {
	const [moduleName, setModule] = useState(null)
	const Module = modules[moduleName]
	return (
		<>
			<StatusBar barStyle="dark-content" />
			<SafeAreaView>
				{(moduleName && <Module back={() => setModule(null)} />) || (
					<>
						<Button
							title="Gesture"
							onPress={() => setModule('Gesture')}
							testID={'Gesture'}
							accessibilityLabel={'Gesture'}
						>
							<Text>Gesture</Text>
						</Button>
						<Button title="Gradient" onPress={() => setModule('Gradient')}>
							<Text>Gradient</Text>
						</Button>
						<Button title="Share" onPress={() => setModule('Share')}>
							<Text>Share</Text>
						</Button>
						<Button title="Udp" onPress={() => setModule('Udp')}>
							<Text>Udp</Text>
						</Button>
						<Button title="Webview" onPress={() => setModule('Webview')}>
							<Text>Webview</Text>
						</Button>
					</>
				)}
			</SafeAreaView>
		</>
	)
}
