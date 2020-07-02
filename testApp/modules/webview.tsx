import React from 'react'
import { Text, View, Button } from 'react-native'
import { WebView } from 'react-native-webview'

export function Webview({ back }) {
	return (
		<>
			<Text>Webview</Text>
			<View style={{ width: '100%', height: 400, backgroundColor: 'blue' }}>
				<WebView source={{ uri: 'https://pixijs.io/examples/#/demos-basic/container.js' }} style={{ height: '100%', width: '100%' }} />
			</View>
			<Button title="BACK" onPress={back}>
				<Text>BACK</Text>
			</Button>
		</>
	)
}
