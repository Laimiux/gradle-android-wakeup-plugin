#!/bin/bash

./gradlew publishToMavenLocal
cd example
./gradlew clean connectedAndroidTest
