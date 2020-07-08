#! /bin/bash

# Usage
# bash isRelease.sh $(git describe --tags)

lastTag=$1
isRelease=$(echo $1 | awk '/rel\//')
if [[ $isRelease ]] 
then
  echo true
else
  echo false
fi
