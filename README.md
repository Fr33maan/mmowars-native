# Build

- Create a keystore
- edit gradle.properties and add keystore password
- download google-services.json
- download api-google-service-account.json
- `fastlane android build_release`

# Aditionnal tasks

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
