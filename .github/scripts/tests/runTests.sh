#! /bin/bash

# isMajorReleaseTest
res=$(bash isMajorReleaseTest.sh)
if [[ $(echo "$res" | awk '/FAILED/') ]]
then
  echo $res
  exit 1
fi

# isReleaseTest
res=$(bash isReleaseTest.sh)
if [[ $(echo "$res" | awk '/FAILED/') ]]
then
  echo $res
  exit 1
fi

# isValidTagTest
res=$(bash isValidTagTest.sh)
if [[ $(echo "$res" | awk '/FAILED/') ]]
then
  echo $res
  exit 1
fi

# getVersionCodeTest
res=$(bash getVersionCodeTest.sh)
if [[ $(echo "$res" | awk '/FAILED/') ]]
then
  echo $res
  exit 1
fi

# tagIsIncremented
res=$(bash tagIsIncrementedTest.sh)
if [[ $(echo "$res" | awk '/FAILED/') ]]
then
  echo $res
  exit 1
fi

echo "Bash tests finished running"