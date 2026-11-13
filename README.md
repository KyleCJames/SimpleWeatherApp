# Simple Weather App

A Kotlin Android demonstration that looks up current weather by city using the OpenWeatherMap API.

## Features
- Enter a city name
- Fetch current weather over HTTPS
- Show temperature (°C) and a weather description
- Display error messages if a request fails

## Tech stack
Kotlin, Android SDK, Android Studio, OkHttp, Kotlin coroutines, OpenWeatherMap API.

## API configuration
**No API key is included in this repository.** Create an API key with OpenWeatherMap. In your local project root's `local.properties` file, add:

```properties
OPENWEATHER_API_KEY=your_key_here
```

This key is supplied at build time via Gradle `BuildConfig`. `local.properties` is excluded from Git; never commit your real key. For a production app, prefer a backend proxy because an API key compiled into a mobile app can still be extracted.

## Running locally
Open in Android Studio, sync Gradle, set the API key in `local.properties`, and run on an emulator or Android device with internet access.

## Notes
Portfolio demonstration; does not include automated network tests or production hardening.
