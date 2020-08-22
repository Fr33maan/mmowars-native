# Goal of repo

Opinionated React-Native boilerplate, already hot, ready for profuction.

# What does it handle for you

- building apps
- running native tests
- distribution with fastlane
- semantic versioning of the app with release to the correct channels
- hotpatch production apps with codepush

# Pre requisites

## ReactNative

- provide `GoogleService-Info.plist` for development with your bundle identifier and paste it in `ios/`
- provide `api-google-service-account.json` for developement with your bundle identifier and place it in `android/app/`
- create a babel.config.js containing the `babel-plugin-module-resolver` config and place it in the ui repo under `native` directory as following:

```
.
├── package.json
├── native
│   └── babel.config.js
├── src
│   └── index.tsx
```

## Android

- create a keystore
- create your app in firebase
- create a json account access file for google play

## Set up github secrets

Some secrets are necessary to run the github actions.  
Paste the content of json files in the correct secrets names.  
Make an hexdump of your keystore and paste the output in the correct secret.

For getting your keystore hexdump:  
`xxd upload-key.keystore upload-key.keystore.hex`

- KEYSTORE_HEX - Hex dump of the android keystore binary
- KEYSTORE_PASSWORD - Password of the android keystore
- GOOGLE_SERVICE_ACCOUNT_JSON - Used to connect fastlane to Google Play
- GOOGLE_SERVICES_JSON - Used to connect android app to Firebase
- PRIVATE_REPO - path of github private repo for ui code
- PAT_PRIVATE_GITHUB_REPO - Private Access Token for the github private repo
- APPCENTER_TOKEN - The API token for appcenter needed by codepush
- VERSION_FILE_JWT_PKEY - Create a file with version name and JWT containing encoded version for server checking
- CODEPUSH_API_TOKEN - the codepush API Token used by the CLI to release a new version
- CODEPUSH_ANDROID_APP_NAME - the appcenter name of the android app your are releasing via codepush
- CODEPUSH_ANDROID_DEPLOYMENT_KEY - the deployment key which goes into `android/app/src/main/res/values/strings.xml` -> [see codepush doc](https://github.com/microsoft/react-native-code-push/blob/master/docs/setup-android.md#plugin-installation-and-configuration-for-react-native-060-version-and-above-android)
- AWS_CLI_ACCESS_KEY_ID - Key ID for IAM AWS S3 Upload role
- AWS_CLI_ACCESS_KEY_SECRET - API Secret for IAM AWS S3 Upload role
- AWS_SOURCEMAP_BUCKET_NAME - Bucket name for sourcemaps
- AWS_SOURCEMAP_REGION - Bucket region for the sourcemap files

# Usage

- Setup secrets
- create a webhook in your UI Private repo which triggers this repo release action when you create a tag on master

## Versioning

The repo uses your UI repo tags to set the version code and version name.  
It must be in the following format: `rel/x.x.x` or `patch/x.x.x`.

- use rel/x.x.x to release on both codepush and stores
- use patch/x.x.x to release only on codepush

**Important notes**

- semantic version number must be incremented
- a major version change will never do a codepush update but only a store update. Your are responsible to block app usage or prompt your users to manually update the app through the store.

# Release process

## Android

**We use a single version instead of staging/release**

#### Store

We push the new version in open beta for testers which can be promoted manually in google play console.

#### Codepush

By default, the app build get a production deployment key.  
You are responsible to override this key for your testers as shown [here in CodePush documentation](https://github.com/microsoft/react-native-code-push#dynamic-deployment-assignment).  
The CI push the new release in the staging branch, disabled and non mandatory. You must manually enable it and make it mandatory or even set a rollout. This is done to not force the operator.

# Build without github actions

- Create a keystore
- edit gradle.properties and add keystore password
- download google-services.json
- download api-google-service-account.json
- `fastlane android build_release`

# Additionnal tasks

This repo has a RN 0.62 base. To update it's sometimes easier to start a fresh repo.  
Some steps must be done due additionaly from modules documentations.

## When using CodePush

With 0.62, need to check if it's solved in 0.63+

Due to a bug with codepush package, we need to update `com.google.gms:google-services` to `4.3.3`  
In `android/build.gradle`

```
buildscript {
  dependencies {
      classpath("com.android.tools.build:gradle:3.5.2")
      classpath("com.google.gms:google-services:4.3.3") // Updated for codepush
  }
}
```
