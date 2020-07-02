import React from 'react'
import { Text, Button } from 'react-native'

export function Share({ back }) {
	return (
		<>
			<Text>Share</Text>
			<Button title="BACK" onPress={back}>
				<Text>BACK</Text>
			</Button>
		</>
	)
}
