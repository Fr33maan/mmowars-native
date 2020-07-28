import React from 'react'
import { Text, Button } from 'react-native'
import RNShare from 'react-native-share'

function share() {
	RNShare.open({ message: 'a message to share' })
		.then((res) => {
			console.log(res)
		})
		.catch((err) => {
			err && console.log(err)
		})
}

export function Share({ back }) {
	return (
		<>
			<Text>Share</Text>

			<Button title="SHARE" onPress={share}>
				<Text>SHARE</Text>
			</Button>

			<Button title="BACK" onPress={back}>
				<Text>BACK</Text>
			</Button>
		</>
	)
}
