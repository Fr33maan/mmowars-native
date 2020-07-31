#! /bin/bash

testIncrementMajor() {
  tags=("rel/0.0.1" "patch/0.0.2" "rel/1.0.0")
  isIncremented=$(bash ../tagIsIncremented.sh ${tags[*]})
  assertTrue "last tag should be incremented" "${isIncremented}"
}

testIncrementMinor() {
  tags=("rel/0.0.1" "patch/0.0.2" "rel/0.1.0")
  isIncremented=$(bash ../tagIsIncremented.sh ${tags[*]})
  assertTrue "last tag should be incremented" "${isIncremented}"
}

testIncrementPatch() {
  tags=("rel/0.0.1" "patch/0.0.2" "rel/0.0.3")
  isIncremented=$(bash ../tagIsIncremented.sh ${tags[*]})
  assertTrue "last tag should be incremented" "${isIncremented}"
}

testDecrementMajor() {
  tags=("rel/0.0.1" "patch/1.0.2" "rel/0.1.0")
  isIncremented=$(bash ../tagIsIncremented.sh ${tags[*]})
  assertFalse "last tag should be decremented" "${isIncremented}"
}

testDecrementMinor() {
  tags=("rel/0.0.1" "patch/1.1.2" "rel/1.0.4")
  isIncremented=$(bash ../tagIsIncremented.sh ${tags[*]})
  assertFalse "last tag should be decremented" "${isIncremented}"
}

testDecrementPatch() {
  tags=("rel/0.0.1" "patch/1.1.2" "rel/1.1.1")
  isIncremented=$(bash ../tagIsIncremented.sh ${tags[*]})
  assertFalse "last tag should be decremented" "${isIncremented}"
}

testSameVersion() {
  tags=("rel/0.0.1" "patch/1.1.2" "rel/1.1.2")
  isIncremented=$(bash ../tagIsIncremented.sh ${tags[*]})
  assertFalse "last tag should be same" "${isIncremented}"
}

# Load shUnit2.
. shunit2