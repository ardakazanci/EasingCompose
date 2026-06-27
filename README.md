# Easing

Motion curves for Jetpack Compose, without the mystery.

[Türkçe](README.tr.md) | [English](README.en.md)

Easing is an early-stage animation library and demo playground for mobile interfaces.
It helps developers explore, preview, and reuse easing curves with a clean Kotlin core and a focused Compose adapter.

## Preview

<video src="docs/media/easing.mp4" controls muted loop playsinline width="720"></video>

[Watch the demo video](docs/media/easing.mp4)

## Quick Start

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

## License

Licensed under the Apache License, Version 2.0.
See [LICENSE.md](LICENSE.md).
