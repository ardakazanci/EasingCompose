package com.ardakazanci.easing.core

data class EasingDefinition(
    val id: String,
    val displayName: String,
    val codeName: String,
    val family: EasingFamily,
    val mode: EasingMode,
    val function: EasingFunction,
    val cubicBezier: CubicBezier? = null,
    val allowsOvershoot: Boolean = false,
) {
    fun transform(fraction: Float): Float {
        return function.transform(fraction.coerceIn(0f, 1f))
    }

    fun samplePoints(count: Int = DefaultSampleCount): List<EasingSample> {
        require(count >= MinimumSampleCount) { "count must be at least $MinimumSampleCount" }

        return List(count) { index ->
            val fraction = index.toFloat() / (count - 1).toFloat()
            EasingSample(
                fraction = fraction,
                value = transform(fraction),
            )
        }
    }

    companion object {
        const val DefaultSampleCount = 80
        const val MinimumSampleCount = 2
    }
}

data class EasingSample(
    val fraction: Float,
    val value: Float,
)

data class CubicBezier(
    val x1: Float,
    val y1: Float,
    val x2: Float,
    val y2: Float,
)

enum class EasingFamily(
    val displayName: String,
) {
    Linear("Linear"),
    Sine("Sine"),
    Quad("Quad"),
    Cubic("Cubic"),
    Quart("Quart"),
    Quint("Quint"),
    Expo("Expo"),
    Circ("Circ"),
    Back("Back"),
    Elastic("Elastic"),
    Bounce("Bounce"),
}

enum class EasingMode(
    val displayName: String,
) {
    None("None"),
    In("In"),
    Out("Out"),
    InOut("In Out"),
}
