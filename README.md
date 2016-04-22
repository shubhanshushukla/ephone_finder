# Release Notes

### 2015-10-15

* All fragments have been updated to support lib fragments.
* All activities are now AppCompat
* Dagger is updated to Dagger 2
* ButterKnife has been updated to 7.0.1
* Timer notification now shows the timer in the notification (useful for when someone does not have the app open)
* No ETA on the Material and RxJava implementation other than "when there is time". That magical time is wild stuff.

# UPDATE: 2014-11-18

* Smart Helmet is now API 15+ only.
* I will also be implementing RxJava, Material design and a few other things into this app as well.
There will be *no upgrade path* as Smart Helmet is mean to act as a starting point for Android Applications.

# Smart Helmet App

[![Build Status](https://travis-ci.org/AndroidBootstrap/com-experiment-smart-helmet.svg?branch=master)](https://travis-ci.org/AndroidBootstrap/com-experiment-smart-helmet)

This repository contains the source code for the [Smart Helmet](http://www.androidbootstrap.com/)
Android app available from [Google Play](https://play.google.com/store/apps/details?id=com.experiment.smart.helmet).

Please see the [issues](https://github.com.experiment.smart.helmet/com-experiment-smart-helmet/issues) section
to report any bugs or feature requests and to see the list of known issues.

Have a questions about Smart Helmet? Ask away on the [com-experiment-smart-helmet discussion forum](https://groups.google.com/forum/#!forum/com-experiment-smart-helmet).

<a href="https://play.google.com/store/apps/details?id=com.experiment.smart.helmet" alt="Download from Google Play">
  <img src="http://f.cl.ly/items/3V0K1s1i402W0c193v2w/Image%202013.07.08%201%3A45%3A25%20PM.png">
</a>

<a href="https://play.google.com/store/apps/details?id=com.experiment.smart.helmet" alt="Download from Google Play">
  <img src="http://f.cl.ly/items/0e3T2F2x3M0K2l1X0A0u/Image%202013.07.08%201%3A46%3A09%20PM.png">
</a>

## HOW TO
Learn how to develop with IntelliJ and Gradle.

## Authentication
Log into this demo app with the following credentials:

user: demo@androidbootstrap.com

password: android


## Generating your Bootstrap App
Why generate? Simple ... renaming files, folders, copy and pasting is SUPER error prone and well... it sucks overall.
This can easily take a few days with debugging if you run into issues and perform a lot of typo's.
Using the generator on [AndroidBootstrap.com](http://www.androidbootstrap.com) you can generate your application
with your application name as well as the package (and folder structure) that you want to work with.

As an example, you know that you want your app name and package to the following:

  - *App Name*: Notify
  - *Package Name*: com.notify.app.mobile

After generating the app on [AndroidBootstrap.com](http://www.androidbootstrap.com) the folder structure of the source
code for the app will change:

  - From: __com.experiment.smart.helmet__
  - To: __com/notify/app/mobile__

At that point all the source files that were located in ____com.experiment.smart.helmet__ will be moved to the
new folder __com/notify/app/mobile__.

All import statments that reference the old resources (__R.com.experiment.smart.helmet.R__) will now be renamed
to the correct package. The artifact id's in the *pom.xml* (and various other places) will be replaced. The App Name
will be replaced in the strings/etc.

The end result is that you will be given a zip file with the correct structure. Open the zip and then execute the
*./gradlew* command and your app should be ready for development.

Enjoy!

The application

## License

* [Apache Version 2.0](http://www.apache.org/licenses/LICENSE-2.0.html)


Copyright 2014 Donn Felker
Copyright 2014 GitHub Inc.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.


## Building

The build requires [Gradle](http://www.gradle.org/downloads)
v1.10+ and the [Android SDK](http://developer.android.com/sdk/index.html)
to be installed in your development environment. In addition you'll need to set
the `ANDROID_HOME` environment variable to the location of your SDK:

    export ANDROID_HOME=/path/to/your/android-sdk

After satisfying those requirements, the build is pretty simple:

* Run `gradlew` or `gradle assembleDebug` or `gradle assembleRelease` from the `app` directory to build the APK only
* Run one of the commands above from the root directory to build the app and also run
  the integration tests, this requires a connected Android device or running
  emulator.

You might find that your device doesn't let you install your build if you
already have the version from the Android Market installed.  This is standard
Android security as it it won't let you directly replace an app that's been
signed with a different key.  Manually uninstall Smart Helmet from your device and
you will then be able to install your own built version.

## Building in Eclipse

Why are you using Eclipse still? :)
Please use Android Studio, we do not support Eclipse.


## Acknowledgements

Smart Helmet is a result of a template project I've developed over the years as well as
a combination of a lot of great work that the [GitHub Gaug.es](http://www.github.com/github/gauges-android)
app and [GitHub Android](http://www.github.com/github/android) app showcased. Some of the
code in this project is based on the GitHub Gaug.es and GitHub Android app.

Smart Helmet is built on the awesome [Parse.com API](http://www.parse.com/)
and uses many great open-source libraries from the Android dev community:

* [AppCompat](http://www.youtube.com/watch?v=6TGgYqfJnyc) for a
  consistent, great looking header across all Android platforms,
  [ViewPagerIndicator](https://github.com/JakeWharton/Android-ViewPagerIndicator)
  for swiping between fragments and
  [NineOldAndroids](https://github.com/JakeWharton/NineOldAndroids) for
  view animations - all from [Jake Wharton](http://jakewharton.com/).
* [NavigationDrawer](http://developer.android.com/design/patterns/navigation-drawer.html) for the menu drawer navigation.
* [Dagger](https://github.com/square/dagger) for dependency-injection.
* [ButterKnife](https://github.com/JakeWharton/butterknife) for view injection
* [Otto](https://github.com/square/otto) as the event bus
* [Robotium](http://code.google.com/p/robotium/)
  for driving our app during integration tests.
* [android-maven-plugin](https://github.com/jayway/maven-android-plugin)
  for automating our build and producing release-ready APKs.
* [Retrofit](http://square.github.io/retrofit/) for interacting with
  remote HTTP resources (API's in this case).
* [google-gson](http://code.google.com/p/google-gson/) for consuming JSON and hydrating
  POJO's for use in the app.


## Contributors
Thank you to all the [contributors](http://www.github.com.experiment.smart.helmet/com-experiment-smart-helmet/contributors) on
this project. Your help is much appreciated.


## Contributing

Please fork this repository and contribute back using
[pull requests](https://github.com.experiment.smart.helmet/com-experiment-smart-helmet/pulls).

Any contributions, large or small, major features, bug fixes, additional
language translations, unit/integration tests are welcomed and appreciated
but will be thoroughly reviewed and discussed.

I hope this helps you in building your next android app.
