import React from 'react'
import { View, Text, Button, StyleSheet } from 'react-native'
import LinearGradient from 'react-native-linear-gradient'

// Later on in your styles..
var styles = StyleSheet.create({
	linearGradient: {
		borderRadius: 5,
		width: '100%',
		height: 100,
	},
	buttonText: {
		fontSize: 18,
		fontFamily: 'Gill Sans',
		textAlign: 'center',
		margin: 10,
		color: '#ffffff',
		backgroundColor: 'transparent',
	},
})

export function Gradient({ back }) {
	return (
		<View style={{ width: '100%', height: '100%' }}>
			<Text>Gradient</Text>
			<LinearGradient colors={['#4c669f', '#3b5998', '#192f6a']} style={styles.linearGradient}>
				<Text style={styles.buttonText}>Linear gradient test</Text>
			</LinearGradient>
			<Button title="BACK" onPress={back}>
				<Text>BACK</Text>
			</Button>
		</View>
	)
}
