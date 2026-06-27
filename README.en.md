# Easing

[Türkçe](README.tr.md) | [English](README.en.md)

Easing is a Jetpack Compose library concept for reusable, inspectable animation curves.
It is built as both a developer-facing library and a demo playground, so motion can be selected by feel instead of guesswork.

## Architecture

The repository is organized into three modules:

- `:easing-core`: Pure Kotlin. Contains easing definitions, math functions, metadata, and sampled curve points.
- `:easing-compose`: Converts core definitions into Jetpack Compose `Easing` values.
- `:app`: Demo application for browsing curves, changing preview properties, adjusting duration, and reading Compose snippets.

The architecture keeps the math independent from the UI framework.
Core easing rules point inward and do not depend on Android or Compose.
Compose integration lives at the edge as a small adapter layer.

This makes the project easier to test, easier to publish as a library, and easier to evolve toward multiplatform targets later.

## Demo Experience

The demo app is not a landing page.
It is the tool itself:

- choose an easing family
- inspect the curve
- preview position, scale, and alpha
- adjust animation duration
- run, reverse, and reset the motion
- read a Compose usage pattern

The intended feel is quiet, precise, and immediate.
The UI should help the curve explain itself.

## Preview

<video src="docs/media/easing.mp4" controls muted loop playsinline width="720"></video>

[Watch the demo video](docs/media/easing.mp4)

## Usage

```kotlin
val value by animateFloatAsState(
    targetValue = if (expanded) 1f else 0f,
    animationSpec = tween(
        durationMillis = 700,
        easing = ComposeEasings.EaseOutExpo
    ),
    label = "easeOutExpo"
)
```

## Implementation Note

Easing implements common animation timing equations directly in Kotlin and exposes them through a small Jetpack Compose adapter layer.

## Development

Run the core tests and build the debug demo app:

```sh
./gradlew :easing-core:test :app:testDebugUnitTest :app:assembleDebug
```

## License

Licensed under the Apache License, Version 2.0.
See [LICENSE.md](LICENSE.md).
