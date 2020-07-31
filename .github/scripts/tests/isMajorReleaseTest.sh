#! /bin/bash

testIsMajorFromRel() {
  tags=("rel/0.0.1" "patch/0.0.2" "rel/0.0.3" "patch/0.0.4" "rel/0.0.5" "rel/1.0.0")
  isMajor=$(bash ../isMajorRelease.sh ${tags[*]})
  assertTrue "last tag should be a major" "${isMajor}"
}

testIsMajorFromPatch() {
  tags=("rel/0.0.1" "patch/0.0.2" "rel/0.0.3" "patch/0.0.4" "patch/0.1.5" "rel/1.0.0")
  isMajor=$(bash ../isMajorRelease.sh ${tags[*]})
  assertTrue "last tag should be a major" "${isMajor}"
}

testIsNotMajorFromRel() {
  tags=("rel/0.0.1" "patch/0.0.2" "rel/0.0.3" "patch/0.0.4" "rel/0.0.5" "rel/0.1.0")
  isMajor=$(bash ../isMajorRelease.sh ${tags[*]})
  assertFalse "last tag should not be a major release" "${isMajor}"
}

testIsNotMajorFromPatch() {
  tags=("rel/0.0.1" "patch/0.0.2" "rel/0.0.3" "patch/0.0.4" "patch/0.0.5" "rel/0.1.0")
  isMajor=$(bash ../isMajorRelease.sh ${tags[*]})
  assertFalse "last tag should not be a major release" "${isMajor}"
}

# Load shUnit2.
. shunit2