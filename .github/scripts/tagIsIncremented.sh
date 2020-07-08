#! /bin/bash

# Description
# Will check the __LAST__ tag on the branch and return true if it has been incremented since the last version tag

# Usage
# bash tagIsIncremented.sh $(git tag --sort=committerdate)

# preLast tag
preLastIndex=$(($#-1))
preLastTag=${!preLastIndex}

# replace the / character by space and create an array from the string
preLastTagArr=($(echo $preLastTag | tr "/" " "))

# Get the separate version codes major.minor.patch
# replace the . character by space and create an array from the string
preLastVersionArr=($(echo ${preLastTagArr[1]} | tr "." " "))

# lastTag
lastIndex=$#
lastTag=${!lastIndex}

# replace the / character by space and create an array from the string
lastTagArr=($(echo $lastTag | tr "/" " "))
lastTagType=${lastTagArr[0]}

# Get the separate version codes major.minor.patch
# replace the . character by space and create an array from the string
lastVersionArr=($(echo ${lastTagArr[1]} | tr "." " "))

# Check that last version is an increment from preLastVersion
if [[ "${lastVersionArr[0]}" -gt "${preLastVersionArr[0]}" ]]
then
  isIncremented=true
else
  if [[ "${lastVersionArr[0]}" -lt "${preLastVersionArr[0]}" ]]
  then
    isIncremented=false
  else
    if [[ "${lastVersionArr[1]}" -gt "${preLastVersionArr[1]}" ]]
    then
      isIncremented=true
    else
      if [[ "${lastVersionArr[1]}" -lt "${preLastVersionArr[1]}" ]]
      then
        isIncremented=false
      else
        if [[ "${lastVersionArr[2]}" -gt "${preLastVersionArr[2]}" ]]
        then
          isIncremented=true
        else
          isIncremented=false
        fi
      fi
    fi
  fi
fi

echo "$isIncremented"