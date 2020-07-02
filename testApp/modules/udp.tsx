import React, { useEffect, useState } from 'react'
import { Text, Button } from 'react-native'
var dgram = require('react-native-udp')

const ids = {
	lastId: 0,
}

function getNextId(): { int: number; bytes: number[] } {
	if (ids.lastId === 65535) {
		ids.lastId = 0
	} else {
		ids.lastId++
	}

	return {
		int: ids.lastId,
		bytes: [(ids.lastId & 0x0000ff00) >> 8, ids.lastId & 0x000000ff],
	}
}

function sendMessage(socket, setResponsesCount) {
	const { int, bytes } = getNextId()
	if (int % 200 === 0) {
		setResponsesCount(int)
	}

	const buffer = new Uint8Array(1000)
	buffer[0] = bytes[0]
	buffer[1] = bytes[1]

	socket.send(buffer, 0, 1000, 4444, '127.0.0.1', function (err) {
		if (err) throw err
		// console.log('message was sent ' + int)
	})
}

export function Udp({ back }) {
	const [responsesCount, setResponsesCount] = useState(0)

	useEffect(() => {
		const socket = dgram.createSocket('udp4')
		socket.bind(4445)

		socket.once('listening', () => {
			sendMessage(socket, setResponsesCount)
		})

		socket.on('message', (msg) => {
			// console.log('message was received ' + (msg[0] * 256 + msg[1]))
			sendMessage(socket, setResponsesCount)
		})
	}, [])

	return (
		<>
			<Text>Udp</Text>
			<Text accessibilityLabel="responsesCount">{responsesCount}</Text>
			<Button title="BACK" onPress={back}>
				<Text>BACK</Text>
			</Button>
		</>
	)
}
