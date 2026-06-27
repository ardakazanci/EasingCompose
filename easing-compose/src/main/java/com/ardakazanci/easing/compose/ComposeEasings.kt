package com.ardakazanci.easing.compose

import androidx.compose.animation.core.Easing
import com.ardakazanci.easing.core.EasingDefinition
import com.ardakazanci.easing.core.StandardEasings

object ComposeEasings {
    val Linear: Easing = StandardEasings.Linear.toComposeEasing()

    val EaseInSine: Easing = StandardEasings.EaseInSine.toComposeEasing()
    val EaseOutSine: Easing = StandardEasings.EaseOutSine.toComposeEasing()
    val EaseInOutSine: Easing = StandardEasings.EaseInOutSine.toComposeEasing()

    val EaseInQuad: Easing = StandardEasings.EaseInQuad.toComposeEasing()
    val EaseOutQuad: Easing = StandardEasings.EaseOutQuad.toComposeEasing()
    val EaseInOutQuad: Easing = StandardEasings.EaseInOutQuad.toComposeEasing()

    val EaseInCubic: Easing = StandardEasings.EaseInCubic.toComposeEasing()
    val EaseOutCubic: Easing = StandardEasings.EaseOutCubic.toComposeEasing()
    val EaseInOutCubic: Easing = StandardEasings.EaseInOutCubic.toComposeEasing()

    val EaseInQuart: Easing = StandardEasings.EaseInQuart.toComposeEasing()
    val EaseOutQuart: Easing = StandardEasings.EaseOutQuart.toComposeEasing()
    val EaseInOutQuart: Easing = StandardEasings.EaseInOutQuart.toComposeEasing()

    val EaseInQuint: Easing = StandardEasings.EaseInQuint.toComposeEasing()
    val EaseOutQuint: Easing = StandardEasings.EaseOutQuint.toComposeEasing()
    val EaseInOutQuint: Easing = StandardEasings.EaseInOutQuint.toComposeEasing()

    val EaseInExpo: Easing = StandardEasings.EaseInExpo.toComposeEasing()
    val EaseOutExpo: Easing = StandardEasings.EaseOutExpo.toComposeEasing()
    val EaseInOutExpo: Easing = StandardEasings.EaseInOutExpo.toComposeEasing()

    val EaseInCirc: Easing = StandardEasings.EaseInCirc.toComposeEasing()
    val EaseOutCirc: Easing = StandardEasings.EaseOutCirc.toComposeEasing()
    val EaseInOutCirc: Easing = StandardEasings.EaseInOutCirc.toComposeEasing()

    val EaseInBack: Easing = StandardEasings.EaseInBack.toComposeEasing()
    val EaseOutBack: Easing = StandardEasings.EaseOutBack.toComposeEasing()
    val EaseInOutBack: Easing = StandardEasings.EaseInOutBack.toComposeEasing()

    val EaseInElastic: Easing = StandardEasings.EaseInElastic.toComposeEasing()
    val EaseOutElastic: Easing = StandardEasings.EaseOutElastic.toComposeEasing()
    val EaseInOutElastic: Easing = StandardEasings.EaseInOutElastic.toComposeEasing()

    val EaseInBounce: Easing = StandardEasings.EaseInBounce.toComposeEasing()
    val EaseOutBounce: Easing = StandardEasings.EaseOutBounce.toComposeEasing()
    val EaseInOutBounce: Easing = StandardEasings.EaseInOutBounce.toComposeEasing()

    fun from(definition: EasingDefinition): Easing {
        return definition.toComposeEasing()
    }
}
