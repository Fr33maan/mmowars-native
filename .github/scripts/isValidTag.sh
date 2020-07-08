#! /bin/bash

# Description
# Will check the __LAST__ tag on the branch and return true if it's a valid tag.
# ATTENTION: If the tag is not on the last commit, this will fail (intended behaviour)
# because git describe --tags does not return a valid tag format if tag is on previous commit

# Usage
# bash isValidTag.sh $(git describe --tags)

lastTag=$1
isValidTag=$(echo $1 | awk '/^(patch|rel)\/[0-9]+\.[0-9]+\.[0-9]+$/')
if [[ $isValidTag ]] 
then
  echo true
else
  echo "$1 is not a valid tag"
  echo false
fi
