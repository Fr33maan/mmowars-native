#! /bin/bash

# Usage
# bash getVersionCode.sh $(git tag --sort=committerdate)

versionCode=1

for var in "$@"
do
    isRelease=$(echo $var | awk '/rel\//')
    if [[ $isRelease ]] 
    then
      versionCode=$(($versionCode+1))
    fi
done
echo $(($versionCode + 9))
