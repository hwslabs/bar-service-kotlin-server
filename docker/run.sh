#!/bin/bash

# Initialise arguments as variables
for arg in "$@"
do
  map=(${arg//=/ })
  declare ${map[0]}=${map[1]}
done

# Delete build only if argument clean_build=true
if [[ $clean_build == true ]]
then
  ./gradlew clean
fi

./gradlew build --build-cache
./gradlew installDist

cp -rf ./$SERVER_PATH/build/install/$SERVER_PATH ./runner
ls | grep -v "docker\|runner\|build" | xargs rm -r
./runner/bin/$EXECUTABLE_NAME
