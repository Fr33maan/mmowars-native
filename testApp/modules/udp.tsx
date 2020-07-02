import React from 'react'
import { Text, Button } from 'react-native'

export function Udp({ back }) {
	return (
		<>
			<Text>Udp</Text>
			<Button title="BACK" onPress={back}>
				<Text>BACK</Text>
			</Button>
		</>
	)
}
