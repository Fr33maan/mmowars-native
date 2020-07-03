import React, { useEffect, useState } from 'react'
import { Text, Button, View } from 'react-native'
import udp from 'react-native-udp'

const ids = {
	lastId: 0,
}

function sendMessage(socket, setResponsesCount) {
	const currentId = ids.lastId++
	if (currentId % 200 === 0) {
		// Limit number of UI updates to not interfere with throughput
		setResponsesCount(currentId)
	}

	// Send messages of 1000 bytes to simulate MTU
	socket.send(new Uint8Array(1000), 0, 1000, 4444, '127.0.0.1', function (err) {
		if (err) throw err
	})
}

function UdpClient({ setStateMessage, setErrorMessage, setResponsesCount, setUnmount }) {
	useEffect(() => {
		try {
			const socket = udp.createSocket('udp4')
			socket.bind((Math.random() * 60536) | (0 + 5000)) // 60536-65536
			setStateMessage('binding')

			socket.once('listening', () => {
				setStateMessage('bound')
				sendMessage(socket, setResponsesCount)
			})

			socket.on('message', (msg) => {
				sendMessage(socket, setResponsesCount)
			})

			socket.on('error', (e) => {
				console.log('error')
				if (typeof e === 'string') {
					setErrorMessage(e)
				} else {
					setErrorMessage(e.message)
				}

				// If error, we unmount and remount
				setUnmount()
			})

			return () => {
				console.log('close')
				socket.close()
			}
		} catch (e) {
			console.log('catch')
			if (typeof e === 'string') {
				setErrorMessage(e)
			} else {
				setErrorMessage(e.message)
			}
			setUnmount()
		}
	}, [])

	return <></>
}

export function Udp({ back }) {
	const [responsesCount, setResponsesCount] = useState(0)
	const [errorMessage, setErrorMessage] = useState(null)
	const [stateMessage, setStateMessage] = useState(null)
	const [mounted, setMounted] = useState(true)

	// This is to prevent the issue mentioned here
	// https://github.com/tradle/react-native-udp/issues/118
	function setUnmount() {
		setTimeout(() => {
			setMounted(false)
			setErrorMessage(null)
			setStateMessage('remounting')

			setTimeout(() => {
				setMounted(true)
			}, 10)
		}, 10)
	}

	return (
		<View style={{ height: '100%', width: '100%' }}>
			<Text>Udp</Text>
			<Text>State: {stateMessage}</Text>
			<Text>{errorMessage}</Text>
			<Text accessibilityLabel="responsesCount">{responsesCount}</Text>
			{mounted && (
				<UdpClient
					setResponsesCount={setResponsesCount}
					setErrorMessage={setErrorMessage}
					setStateMessage={setStateMessage}
					setUnmount={setUnmount}
				/>
			)}

			<Button title="BACK" onPress={back}>
				<Text>BACK</Text>
			</Button>
		</View>
	)
}
