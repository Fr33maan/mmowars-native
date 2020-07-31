const childProcess = require('child_process')
const fs = require('fs')
const jwt = require('jsonwebtoken')

describe('createJsonVersionFile', () => {
	test('should create the file', () => {
		const privateKey = fs.readFileSync('./.github/scripts/jwtKeys/testJwtRS256.key', 'utf8')
		process.env.VERSION_FILE_JWT_PKEY = privateKey

		// Create the dir if it does not exists
		try {
			fs.mkdirSync('./App/prodApp/config')
		} catch {}

		const versionName = '1.0.0'

		// Exec the command as github action will do
		childProcess.execSync(
			`export VERSION_FILE_JWT_PKEY="${privateKey}" && node ./.github/scripts/createJsonVersionFile.js 1.0.0`,
		)

		const file = JSON.parse(fs.readFileSync('./App/prodApp/config/versionName.json', 'utf8'))

		// Clear text version name should be correct
		expect(file.versionName).toBe('1.0.0')

		// Version name should be correct once decoded
		const tokenContent = jwt.decode(file.token, { privateKey })
		expect(tokenContent.versionName).toBe(versionName)
	})
})
