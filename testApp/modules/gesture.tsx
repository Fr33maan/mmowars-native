import React from 'react'
import { Text, Button } from 'react-native'

export function Gesture({ back }) {
	return (
		<>
			<Text>Gesture</Text>
			<Button title="BACK" onPress={back}>
				<Text>BACK</Text>
			</Button>
		</>
	)
}
