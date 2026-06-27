package com.ardakazanci.easing.core

import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt

object StandardEasings {
    val Linear = easing(
        codeName = "Linear",
        family = EasingFamily.Linear,
        mode = EasingMode.None,
        cubicBezier = CubicBezier(0f, 0f, 1f, 1f),
    ) { it }

    val EaseInSine = easing(
        codeName = "EaseInSine",
        family = EasingFamily.Sine,
        mode = EasingMode.In,
        cubicBezier = CubicBezier(0.12f, 0f, 0.39f, 0f),
    ) { t ->
        1f - cos((t * PI.toFloat()) / 2f)
    }

    val EaseOutSine = easing(
        codeName = "EaseOutSine",
        family = EasingFamily.Sine,
        mode = EasingMode.Out,
        cubicBezier = CubicBezier(0.61f, 1f, 0.88f, 1f),
    ) { t ->
        sin((t * PI.toFloat()) / 2f)
    }

    val EaseInOutSine = easing(
        codeName = "EaseInOutSine",
        family = EasingFamily.Sine,
        mode = EasingMode.InOut,
        cubicBezier = CubicBezier(0.37f, 0f, 0.63f, 1f),
    ) { t ->
        -((cos(PI.toFloat() * t) - 1f) / 2f)
    }

    val EaseInQuad = easing(
        codeName = "EaseInQuad",
        family = EasingFamily.Quad,
        mode = EasingMode.In,
        cubicBezier = CubicBezier(0.11f, 0f, 0.5f, 0f),
    ) { t ->
        t * t
    }

    val EaseOutQuad = easing(
        codeName = "EaseOutQuad",
        family = EasingFamily.Quad,
        mode = EasingMode.Out,
        cubicBezier = CubicBezier(0.5f, 1f, 0.89f, 1f),
    ) { t ->
        1f - (1f - t) * (1f - t)
    }

    val EaseInOutQuad = easing(
        codeName = "EaseInOutQuad",
        family = EasingFamily.Quad,
        mode = EasingMode.InOut,
        cubicBezier = CubicBezier(0.45f, 0f, 0.55f, 1f),
    ) { t ->
        if (t < 0.5f) 2f * t * t else 1f - (-2f * t + 2f).pow(2) / 2f
    }

    val EaseInCubic = easing(
        codeName = "EaseInCubic",
        family = EasingFamily.Cubic,
        mode = EasingMode.In,
        cubicBezier = CubicBezier(0.32f, 0f, 0.67f, 0f),
    ) { t ->
        t * t * t
    }

    val EaseOutCubic = easing(
        codeName = "EaseOutCubic",
        family = EasingFamily.Cubic,
        mode = EasingMode.Out,
        cubicBezier = CubicBezier(0.33f, 1f, 0.68f, 1f),
    ) { t ->
        1f - (1f - t).pow(3)
    }

    val EaseInOutCubic = easing(
        codeName = "EaseInOutCubic",
        family = EasingFamily.Cubic,
        mode = EasingMode.InOut,
        cubicBezier = CubicBezier(0.65f, 0f, 0.35f, 1f),
    ) { t ->
        if (t < 0.5f) 4f * t * t * t else 1f - (-2f * t + 2f).pow(3) / 2f
    }

    val EaseInQuart = easing(
        codeName = "EaseInQuart",
        family = EasingFamily.Quart,
        mode = EasingMode.In,
        cubicBezier = CubicBezier(0.5f, 0f, 0.75f, 0f),
    ) { t ->
        t * t * t * t
    }

    val EaseOutQuart = easing(
        codeName = "EaseOutQuart",
        family = EasingFamily.Quart,
        mode = EasingMode.Out,
        cubicBezier = CubicBezier(0.25f, 1f, 0.5f, 1f),
    ) { t ->
        1f - (1f - t).pow(4)
    }

    val EaseInOutQuart = easing(
        codeName = "EaseInOutQuart",
        family = EasingFamily.Quart,
        mode = EasingMode.InOut,
        cubicBezier = CubicBezier(0.76f, 0f, 0.24f, 1f),
    ) { t ->
        if (t < 0.5f) 8f * t * t * t * t else 1f - (-2f * t + 2f).pow(4) / 2f
    }

    val EaseInQuint = easing(
        codeName = "EaseInQuint",
        family = EasingFamily.Quint,
        mode = EasingMode.In,
        cubicBezier = CubicBezier(0.64f, 0f, 0.78f, 0f),
    ) { t ->
        t * t * t * t * t
    }

    val EaseOutQuint = easing(
        codeName = "EaseOutQuint",
        family = EasingFamily.Quint,
        mode = EasingMode.Out,
        cubicBezier = CubicBezier(0.22f, 1f, 0.36f, 1f),
    ) { t ->
        1f - (1f - t).pow(5)
    }

    val EaseInOutQuint = easing(
        codeName = "EaseInOutQuint",
        family = EasingFamily.Quint,
        mode = EasingMode.InOut,
        cubicBezier = CubicBezier(0.83f, 0f, 0.17f, 1f),
    ) { t ->
        if (t < 0.5f) 16f * t * t * t * t * t else 1f - (-2f * t + 2f).pow(5) / 2f
    }

    val EaseInExpo = easing(
        codeName = "EaseInExpo",
        family = EasingFamily.Expo,
        mode = EasingMode.In,
        cubicBezier = CubicBezier(0.7f, 0f, 0.84f, 0f),
    ) { t ->
        if (t == 0f) 0f else 2f.pow(10f * t - 10f)
    }

    val EaseOutExpo = easing(
        codeName = "EaseOutExpo",
        family = EasingFamily.Expo,
        mode = EasingMode.Out,
        cubicBezier = CubicBezier(0.16f, 1f, 0.3f, 1f),
    ) { t ->
        if (t == 1f) 1f else 1f - 2f.pow(-10f * t)
    }

    val EaseInOutExpo = easing(
        codeName = "EaseInOutExpo",
        family = EasingFamily.Expo,
        mode = EasingMode.InOut,
        cubicBezier = CubicBezier(0.87f, 0f, 0.13f, 1f),
    ) { t ->
        when {
            t == 0f -> 0f
            t == 1f -> 1f
            t < 0.5f -> 2f.pow(20f * t - 10f) / 2f
            else -> (2f - 2f.pow(-20f * t + 10f)) / 2f
        }
    }

    val EaseInCirc = easing(
        codeName = "EaseInCirc",
        family = EasingFamily.Circ,
        mode = EasingMode.In,
        cubicBezier = CubicBezier(0.55f, 0f, 1f, 0.45f),
    ) { t ->
        1f - sqrt(1f - t * t)
    }

    val EaseOutCirc = easing(
        codeName = "EaseOutCirc",
        family = EasingFamily.Circ,
        mode = EasingMode.Out,
        cubicBezier = CubicBezier(0f, 0.55f, 0.45f, 1f),
    ) { t ->
        sqrt(1f - (t - 1f) * (t - 1f))
    }

    val EaseInOutCirc = easing(
        codeName = "EaseInOutCirc",
        family = EasingFamily.Circ,
        mode = EasingMode.InOut,
        cubicBezier = CubicBezier(0.85f, 0f, 0.15f, 1f),
    ) { t ->
        if (t < 0.5f) {
            (1f - sqrt(1f - (2f * t).pow(2))) / 2f
        } else {
            (sqrt(1f - (-2f * t + 2f).pow(2)) + 1f) / 2f
        }
    }

    val EaseInBack = easing(
        codeName = "EaseInBack",
        family = EasingFamily.Back,
        mode = EasingMode.In,
        cubicBezier = CubicBezier(0.36f, 0f, 0.66f, -0.56f),
        allowsOvershoot = true,
    ) { t ->
        val c1 = 1.70158f
        val c3 = c1 + 1f
        c3 * t * t * t - c1 * t * t
    }

    val EaseOutBack = easing(
        codeName = "EaseOutBack",
        family = EasingFamily.Back,
        mode = EasingMode.Out,
        cubicBezier = CubicBezier(0.34f, 1.56f, 0.64f, 1f),
        allowsOvershoot = true,
    ) { t ->
        val c1 = 1.70158f
        val c3 = c1 + 1f
        1f + c3 * (t - 1f).pow(3) + c1 * (t - 1f).pow(2)
    }

    val EaseInOutBack = easing(
        codeName = "EaseInOutBack",
        family = EasingFamily.Back,
        mode = EasingMode.InOut,
        cubicBezier = CubicBezier(0.68f, -0.6f, 0.32f, 1.6f),
        allowsOvershoot = true,
    ) { t ->
        val c1 = 1.70158f
        val c2 = c1 * 1.525f

        if (t < 0.5f) {
            ((2f * t).pow(2) * ((c2 + 1f) * 2f * t - c2)) / 2f
        } else {
            (((2f * t - 2f).pow(2) * ((c2 + 1f) * (2f * t - 2f) + c2)) + 2f) / 2f
        }
    }

    val EaseInElastic = easing(
        codeName = "EaseInElastic",
        family = EasingFamily.Elastic,
        mode = EasingMode.In,
        allowsOvershoot = true,
    ) { t ->
        val c4 = (2f * PI.toFloat()) / 3f

        when (t) {
            0f -> 0f
            1f -> 1f
            else -> -2f.pow(10f * t - 10f) * sin((t * 10f - 10.75f) * c4)
        }
    }

    val EaseOutElastic = easing(
        codeName = "EaseOutElastic",
        family = EasingFamily.Elastic,
        mode = EasingMode.Out,
        allowsOvershoot = true,
    ) { t ->
        val c4 = (2f * PI.toFloat()) / 3f

        when (t) {
            0f -> 0f
            1f -> 1f
            else -> 2f.pow(-10f * t) * sin((t * 10f - 0.75f) * c4) + 1f
        }
    }

    val EaseInOutElastic = easing(
        codeName = "EaseInOutElastic",
        family = EasingFamily.Elastic,
        mode = EasingMode.InOut,
        allowsOvershoot = true,
    ) { t ->
        val c5 = (2f * PI.toFloat()) / 4.5f

        when {
            t == 0f -> 0f
            t == 1f -> 1f
            t < 0.5f -> -(2f.pow(20f * t - 10f) * sin((20f * t - 11.125f) * c5)) / 2f
            else -> (2f.pow(-20f * t + 10f) * sin((20f * t - 11.125f) * c5)) / 2f + 1f
        }
    }

    val EaseInBounce = easing(
        codeName = "EaseInBounce",
        family = EasingFamily.Bounce,
        mode = EasingMode.In,
    ) { t ->
        1f - bounceOut(1f - t)
    }

    val EaseOutBounce = easing(
        codeName = "EaseOutBounce",
        family = EasingFamily.Bounce,
        mode = EasingMode.Out,
    ) { t ->
        bounceOut(t)
    }

    val EaseInOutBounce = easing(
        codeName = "EaseInOutBounce",
        family = EasingFamily.Bounce,
        mode = EasingMode.InOut,
    ) { t ->
        if (t < 0.5f) {
            (1f - bounceOut(1f - 2f * t)) / 2f
        } else {
            (1f + bounceOut(2f * t - 1f)) / 2f
        }
    }

    val all: List<EasingDefinition> = listOf(
        Linear,
        EaseInSine,
        EaseOutSine,
        EaseInOutSine,
        EaseInQuad,
        EaseOutQuad,
        EaseInOutQuad,
        EaseInCubic,
        EaseOutCubic,
        EaseInOutCubic,
        EaseInQuart,
        EaseOutQuart,
        EaseInOutQuart,
        EaseInQuint,
        EaseOutQuint,
        EaseInOutQuint,
        EaseInExpo,
        EaseOutExpo,
        EaseInOutExpo,
        EaseInCirc,
        EaseOutCirc,
        EaseInOutCirc,
        EaseInBack,
        EaseOutBack,
        EaseInOutBack,
        EaseInElastic,
        EaseOutElastic,
        EaseInOutElastic,
        EaseInBounce,
        EaseOutBounce,
        EaseInOutBounce,
    )

    private fun easing(
        codeName: String,
        family: EasingFamily,
        mode: EasingMode,
        cubicBezier: CubicBezier? = null,
        allowsOvershoot: Boolean = false,
        function: EasingFunction,
    ): EasingDefinition {
        return EasingDefinition(
            id = codeName.replaceFirstChar { it.lowercase() },
            displayName = codeName.toDisplayName(),
            codeName = codeName,
            family = family,
            mode = mode,
            function = function,
            cubicBezier = cubicBezier,
            allowsOvershoot = allowsOvershoot,
        )
    }

    private fun String.toDisplayName(): String {
        return replace(Regex("(?<=[a-z])(?=[A-Z])"), " ")
    }

    private fun bounceOut(t: Float): Float {
        val scale = 7.5625f
        val interval = 2.75f

        return when {
            t < 1f / interval -> scale * t * t
            t < 2f / interval -> {
                val shifted = t - 1.5f / interval
                scale * shifted * shifted + 0.75f
            }
            t < 2.5f / interval -> {
                val shifted = t - 2.25f / interval
                scale * shifted * shifted + 0.9375f
            }
            else -> {
                val shifted = t - 2.625f / interval
                scale * shifted * shifted + 0.984375f
            }
        }
    }
}
