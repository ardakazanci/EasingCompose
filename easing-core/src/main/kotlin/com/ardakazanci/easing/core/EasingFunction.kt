package com.ardakazanci.easing.core

fun interface EasingFunction {
    fun transform(fraction: Float): Float
}
