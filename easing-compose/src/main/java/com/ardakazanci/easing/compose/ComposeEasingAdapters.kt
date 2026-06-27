package com.ardakazanci.easing.compose

import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.Easing
import com.ardakazanci.easing.core.EasingDefinition

fun EasingDefinition.toComposeEasing(): Easing {
    return Easing { fraction -> transform(fraction) }
}

fun EasingDefinition.toComposeCubicBezierEasingOrNull(): Easing? {
    val bezier = cubicBezier

    return if (bezier == null) {
        null
    } else {
        CubicBezierEasing(
            a = bezier.x1,
            b = bezier.y1,
            c = bezier.x2,
            d = bezier.y2,
        )
    }
}
