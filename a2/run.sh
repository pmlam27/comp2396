#!/bin/bash

javac ./src/*.java -d ./target
cd target || exit


if [ $# -eq 0 ]
  then
    echo "No arguments supplied"
    exit 1
fi

java $1

