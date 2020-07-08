#! /bin/bash

testValidRelease() {
  tag="rel/0.0.0"
  isValidTag=$(bash ../isValidTag.sh $(echo "$tag"))
  assertTrue "$tag should be valid" "${isValidTag}"

  tag="rel/0.0.10"
  isValidTag=$(bash ../isValidTag.sh $(echo "$tag"))
  assertTrue "$tag should be valid" "${isValidTag}"

  tag="rel/4531.455.1223"
  isValidTag=$(bash ../isValidTag.sh $(echo "$tag"))
  assertTrue "$tag should be valid" "${isValidTag}"
}



testValidPatch() {
  tag="patch/0.0.0"
  isValidTag=$(bash ../isValidTag.sh $(echo "$tag"))
  assertTrue "$tag should be valid" "${isValidTag}"

  tag="patch/0.10.4566"
  isValidTag=$(bash ../isValidTag.sh $(echo "$tag"))
  assertTrue "$tag should be valid" "${isValidTag}"

  tag="patch/120121321.6546545646.5664654654"
  isValidTag=$(bash ../isValidTag.sh $(echo "$tag"))
  assertTrue "$tag should be valid" "${isValidTag}"
}


testInvalidRelease() {
  tag="patch/0.0.0-"
  isValidTag=$(bash ../isValidTag.sh $(echo "$tag"))
  assertFalse "$tag should be non valid" "${isValidTag}"

  tag="poutch/0.0.0"
  isValidTag=$(bash ../isValidTag.sh $(echo "$tag"))
  assertFalse "$tag should be non valid" "${isValidTag}"

  tag="rel/rc-0.0.0"
  isValidTag=$(bash ../isValidTag.sh $(echo "$tag"))
  assertFalse "$tag should be non valid" "${isValidTag}"

  tag="rel/rc-0.0.0-1"
  isValidTag=$(bash ../isValidTag.sh $(echo "$tag"))
  assertFalse "$tag should be non valid" "${isValidTag}"

  tag="rel/0"
  isValidTag=$(bash ../isValidTag.sh $(echo "$tag"))
  assertFalse "$tag should be non valid" "${isValidTag}"

  tag="rel/0.0"
  isValidTag=$(bash ../isValidTag.sh $(echo "$tag"))
  assertFalse "$tag should be non valid" "${isValidTag}"

  tag="rel/0.0."
  isValidTag=$(bash ../isValidTag.sh $(echo "$tag"))
  assertFalse "$tag should be non valid" "${isValidTag}"
}

# Load shUnit2.
. shunit2