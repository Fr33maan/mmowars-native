#!/bin/bash

mkdir -p ~/.aws
touch ~/.aws/credentials

echo "[default]
aws_access_key_id = ${AWS_CLI_ACCESS_KEY_ID}
aws_secret_access_key = ${AWS_CLI_ACCESS_KEY_SECRET}" > ~/.aws/credentials

aws s3 cp $1 s3://${AWS_SOURCEMAP_BUCKET_NAME}/ui.native.$2.js.map --region ${AWS_SOURCEMAP_REGION}

rm -rf ~/.aws