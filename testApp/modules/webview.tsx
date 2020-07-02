import React from 'react'
import { Text, Button } from 'react-native'

export function Webview({ back }) {
	return (
		<>
			<Text>Webview</Text>
			<Button title="BACK" onPress={back}>
				<Text>BACK</Text>
			</Button>
		</>
	)
}
