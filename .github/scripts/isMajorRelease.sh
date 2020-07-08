#! /bin/bash

# Description
# Check if semantic major version number has changed since last rel

# Usage
# bash isMajorRelease.sh $(git tag --sort=committerdate)

# preLast tag
preLastIndex=$(($#-1))
preLastTag=${!preLastIndex}

# replace the / character by space and create an array from the string
preLastTagArr=($(echo $preLastTag | tr "/" " "))

# Get the separate version codes major.minor.patch
# replace the . character by space and create an array from the string
preLastVersionArr=($(echo ${preLastTagArr[1]} | tr "." " "))
preLastMajor="${preLastVersionArr[0]}"

# lastTag
lastIndex=$#
lastTag=${!lastIndex}

# replace the / character by space and create an array from the string
lastTagArr=($(echo $lastTag | tr "/" " "))
lastTagType=${lastTagArr[0]}

# Get the separate version codes major.minor.patch
# replace the . character by space and create an array from the string
lastVersionArr=($(echo ${lastTagArr[1]} | tr "." " "))
lastMajor="${lastVersionArr[0]}"

# Set the result of the check
if [[ "$preLastMajor" != "$lastMajor" ]]
then
  isMajor=true
else
  isMajor=false
fi

echo $isMajor