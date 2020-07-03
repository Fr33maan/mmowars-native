import React, { useState } from 'react'
import { Text, Button, View, StyleSheet, Animated } from 'react-native'
import { State as GestureState, PanGestureHandler } from 'react-native-gesture-handler'

const styles = StyleSheet.create({
	joystickHolder: {
		position: 'absolute',
		width: 100,
		height: 100,
		bottom: 0,
		left: 0,
	},
	joystickBackground: {
		borderRadius: 50,
		width: 100,
		height: 100,
		backgroundColor: '#0f0',
		position: 'absolute',
		justifyContent: 'center',
		alignItems: 'center',
		overflow: 'visible',
	},
	joystickSurface: {
		borderRadius: 50,
		width: 100,
		height: 100,
	},
})

// Keep variables outside of COmponent to avoid rerendering
const _translateX = new Animated.Value(0)
const _translateY = new Animated.Value(0)

function Joystick({ setTranslateX, setTranslateY, setFinished }) {
	const joyStickGestureEvent = Animated.event(
		[
			{
				nativeEvent: {
					translationX: _translateX,
					translationY: _translateY,
				},
			},
		],
		{
			useNativeDriver: true,
			listener: onGestureEvent,
		},
	)

	function onGestureEvent({ nativeEvent: { translationX, translationY } }) {
		setTranslateX(translationX)
		setTranslateY(translationY)
	}

	function joyStickHandlerChange({ nativeEvent: { state } }) {
		if (state === GestureState.END) {
			setFinished('finished')
		}
	}

	return (
		<View style={styles.joystickHolder}>
			<PanGestureHandler onGestureEvent={joyStickGestureEvent} onHandlerStateChange={joyStickHandlerChange}>
				<Animated.View style={styles.joystickBackground}>
					<Animated.View
						style={[
							styles.joystickSurface,
							{
								transform: [{ translateX: _translateX }, { translateY: _translateY }],
							},
							{
								backgroundColor: 'rgb(255,0,0)',
							},
						]}
						accessibilityLabel="joystickButton"
					/>
				</Animated.View>
			</PanGestureHandler>
		</View>
	)
}

export function Gesture({ back }) {
	const [tranlateX, setTranslateX] = useState(0)
	const [tranlateY, setTranslateY] = useState(0)
	const [finished, setFinished] = useState(false)

	return (
		<View style={{ height: '100%', width: '100%', backgroundColor: 'blue' }}>
			<Text>Gesture</Text>
			<Button title="BACK" onPress={back}>
				<Text>BACK</Text>
			</Button>
			<Text accessibilityLabel="translateX">{tranlateX}</Text>
			<Text accessibilityLabel="translateY">{tranlateY}</Text>
			<Text accessibilityLabel="translateFinished">{finished && 'TRANSLATE FINISHED'}</Text>
			<Joystick setTranslateX={setTranslateX} setTranslateY={setTranslateY} setFinished={setFinished} />
		</View>
	)
}
