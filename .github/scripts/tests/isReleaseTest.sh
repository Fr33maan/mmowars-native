#! /bin/bash

testRelease() {
  tag="rel/0.0.0"
  isRelease=$(bash ../isRelease.sh $(echo "$tag"))
  assertTrue "$tag should be detected as a release but detected as a patch" "${isRelease}"
}

testPatch() {
  tag="patch/0.0.1"
  isRelease=$(bash ../isRelease.sh $(echo "$tag"))
  assertFalse "$tag should be detected as a patch but detected as a release" "${isRelease}"
}

# Load shUnit2.
. shunit2