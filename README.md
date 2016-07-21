# Overview

This is the mini-sized package to start use Appium with iOS mobile Safari browser and on Android browser

## Pre-requisite:

1. MacOS

2. Java

3. Maven

4. Appium 1.5.3

5. Xcode 7.0.1+ with "iPhone 6/iOS9.1" Simulator, for iOS safari mobile browser test

6. Android SDK + VirtualBox + Genymotion for Android default mobile browser test


## Steps to run:

1. Start Appium properly

1.1 for Android, you need to Start Genymotion Android Emulator manually

2. Use any Java IDE, e.g. IntelliJ, to import this maven project,

3. After project is imported, open the source code SampleTest.java, right click method firstTest and run

4. or set environment var, e.g. device=android, and then run step3, will start Android test

## Expected Automate Test Result:

1. Start the mobile simulator (for iOS only; for Android, please start Genymotion Android Emulator manually)

2. Open mobile browser, e.g., iOS safari or Android Browser

3. Visit "http://google.com"

4. Verify if the title is "Google"

5. Quit the driver

