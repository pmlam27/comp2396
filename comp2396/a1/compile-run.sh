#!/bin/bash

javac ./src/*.java -d ./target
cd target || exit
java Square_GUI_Test
