#! /bin/bash

testVersionCode() {
  tags=("rel/0.0.1" "patch/0.0.2" "rel/0.0.3" "patch/0.0.4" "rel/0.0.5" "rel/1.0.0")
  versionCode=$(bash ../getVersionCode.sh ${tags[*]})
  assertEquals 14 $versionCode
}

# Load shUnit2.
. shunit2