const fs = require('fs')
const jwt = require('jsonwebtoken')

if (process.argv.length !== 3) {
	throw new Error('missing versionName or privateKey')
}

const versionName = process.argv[process.argv.length - 1]
const privateKey = process.env.VERSION_FILE_JWT_PKEY

const token = jwt.sign(
	{
		versionName,
	},
	privateKey,
	{
		algorithm: 'RS256',
	},
)

const fileContent = {
	versionName,
	token,
}

fs.writeFileSync('App/prodApp/config/versionName.json', JSON.stringify(fileContent))
