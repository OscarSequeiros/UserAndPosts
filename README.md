# User and Posts
Small application for showing users and their posts.

## Building the app
The app is using [Gradle](https://gradle.org). An executable wrapper for Gradle is provided within the codebase (gradlew and gradlew.bat), so you don't need to install Gradle locally to build the app.
 - Task for building App: `./gradlew assembleRelease`
 - Task for running all test: `./gradlew test`
 
## Project Structure
The project is organized using Clean Architecture.
<!-- blank line -->
Currently, we have only one module:
 - app : Main module for features of th application.

## Stack & Third Party Libraries
| Name | Version |Purpose |
|-------|-------|-------|
| [Android KTX](https://developer.android.com/kotlin/ktx) | 1.6.0 | Kotlin extensions that are included with Android Jetpack |
| [Android AppCompat](https://developer.android.com/jetpack/androidx/releases/appcompat) | 1.3.1 | Allows access to new APIs an older API version of the platform |
| [Android Material](https://material.io/develop/android/docs/getting-started) | 1.4.0 | Google Design system
| [Android Fragment KTX](https://developer.android.com/kotlin/ktx?gclid=Cj0KCQjwwLKFBhDPARIsAPzPi-IXxYMh9GtbWVjXAhX9BcM1a3TBZ9O7ltNgX79E-sZjq_J6_piybMwaAssyEALw_wcB&gclsrc=aw.ds#fragment) | 1.3.6 | Provides a number of extensions to simplify the fragment API |
| [Kotlin](https://developer.android.com/kotlin) | 1.5.30 | Primary development language |
| [Kotlin Coroutines Core](https://kotlinlang.org/docs/coroutines-overview.html) | 1.5.2 | Asynchronous or non-blocking programming |
| [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) | 2.38.1 | Dependency Injection |
| [Ktor](https://ktor.io/) | 1.6.4 | API calls |
| [Room](https://developer.android.com/jetpack/androidx/releases/room) | 2.3.0 | Provides an abstraction layer over SQLite |
| [JUnit](https://github.com/junit-team/junit4) | 4.13.1 | Write Unit test |
| [Mockk](https://mockk.io/) | 1.11.0 | Mocking for Kotlin |
| [Android JUnit](https://developer.android.com/training/testing/set-up-project#add-android-test-libraries) | 1.1.2 | Android Assertions |
| [Espresso](https://developer.android.com/training/testing/espresso) | 3.3.0 | Write Android UI test |