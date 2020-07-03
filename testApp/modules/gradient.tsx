import React from 'react'
import { Text, Button } from 'react-native'

export function Gradient({ back }) {
	return (
		<>
			<Text>Gradient</Text>
			<Button title="BACK" onPress={back}>
				<Text>BACK</Text>
			</Button>
		</>
	)
}