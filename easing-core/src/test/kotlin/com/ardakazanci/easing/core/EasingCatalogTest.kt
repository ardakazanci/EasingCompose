package com.ardakazanci.easing.core

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class EasingCatalogTest {
    @Test
    fun allEasingsStartAtZeroAndEndAtOne() {
        EasingCatalog.all.forEach { easing ->
            assertCloseTo(
                expected = 0f,
                actual = easing.transform(0f),
                message = "${easing.codeName} should start at 0",
            )
            assertCloseTo(
                expected = 1f,
                actual = easing.transform(1f),
                message = "${easing.codeName} should end at 1",
            )
        }
    }

    @Test
    fun boundedEasingsStayInsideUnitRange() {
        val boundedEasings = EasingCatalog.all.filterNot { it.allowsOvershoot }

        boundedEasings.forEach { easing ->
            easing.samplePoints(count = 101).forEach { sample ->
                assertTrue(
                    "${easing.codeName} produced ${sample.value} at ${sample.fraction}",
                    sample.value in -Tolerance..(1f + Tolerance),
                )
            }
        }
    }

    @Test
    fun samplePointsIncludeBothEnds() {
        val points = StandardEasings.EaseOutExpo.samplePoints(count = 5)

        assertEquals(5, points.size)
        assertCloseTo(0f, points.first().fraction)
        assertCloseTo(1f, points.last().fraction)
        assertCloseTo(0f, points.first().value)
        assertCloseTo(1f, points.last().value)
    }

    @Test
    fun catalogCanFindDefinitionsById() {
        assertEquals(StandardEasings.EaseOutBounce, EasingCatalog.findById("easeOutBounce"))
    }

    private fun assertCloseTo(
        expected: Float,
        actual: Float,
        message: String = "Expected $expected but was $actual",
    ) {
        assertEquals(message, expected, actual, Tolerance)
    }

    private companion object {
        const val Tolerance = 0.0001f
    }
}
