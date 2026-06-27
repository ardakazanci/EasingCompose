package com.ardakazanci.easing.demo

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.Button
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ardakazanci.easing.compose.toComposeEasing
import com.ardakazanci.easing.core.EasingCatalog
import com.ardakazanci.easing.core.EasingDefinition
import com.ardakazanci.easing.core.EasingFamily
import com.ardakazanci.easing.core.StandardEasings
import com.ardakazanci.easing.ui.theme.EasingTheme
import kotlin.math.max
import kotlin.math.min
import kotlin.math.roundToInt

@Composable
fun EasingDemoScreen(
    modifier: Modifier = Modifier,
) {
    var selectedFamily by remember { mutableStateOf<EasingFamily?>(null) }
    val definitions = remember(selectedFamily) {
        EasingCatalog.byFamily(selectedFamily)
    }
    var selectedDefinition by remember { mutableStateOf(StandardEasings.EaseOutExpo) }
    var previewProperty by remember { mutableStateOf(PreviewProperty.Position) }
    var durationMillis by remember { mutableIntStateOf(700) }
    var target by remember { mutableFloatStateOf(1f) }

    LaunchedEffect(definitions) {
        if (selectedDefinition !in definitions) {
            selectedDefinition = definitions.first()
        }
    }

    val progress by animateFloatAsState(
        targetValue = target,
        animationSpec = tween(
            durationMillis = durationMillis,
            easing = selectedDefinition.toComposeEasing(),
        ),
        label = "easingPreview",
    )

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(20.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp),
        ) {
            item {
                Header(
                    selectedDefinition = selectedDefinition,
                    durationMillis = durationMillis,
                )
            }

            item {
                FamilyPicker(
                    selectedFamily = selectedFamily,
                    onFamilySelected = { selectedFamily = it },
                )
            }

            item {
                EasingPicker(
                    definitions = definitions,
                    selectedDefinition = selectedDefinition,
                    onDefinitionSelected = { selectedDefinition = it },
                )
            }

            item {
                CurvePanel(selectedDefinition = selectedDefinition)
            }

            item {
                PreviewPanel(
                    progress = progress,
                    previewProperty = previewProperty,
                    selectedDefinition = selectedDefinition,
                    durationMillis = durationMillis,
                    target = target,
                    onTargetChange = { target = it },
                    onPreviewPropertyChange = { previewProperty = it },
                    onDurationChange = { durationMillis = it },
                )
            }

            item {
                CodePanel(
                    selectedDefinition = selectedDefinition,
                    durationMillis = durationMillis,
                )
            }
        }
    }
}

@Composable
private fun Header(
    selectedDefinition: EasingDefinition,
    durationMillis: Int,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        Text(
            text = "Easing Lab",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.SemiBold,
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = selectedDefinition.displayName,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary,
            )
            Text(
                text = "${durationMillis}ms",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}

@Composable
private fun FamilyPicker(
    selectedFamily: EasingFamily?,
    onFamilySelected: (EasingFamily?) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        item {
            FilterChip(
                selected = selectedFamily == null,
                onClick = { onFamilySelected(null) },
                label = { Text("All") },
            )
        }
        items(EasingCatalog.families, key = { it.name }) { family ->
            FilterChip(
                selected = selectedFamily == family,
                onClick = { onFamilySelected(family) },
                label = { Text(family.displayName) },
            )
        }
    }
}

@Composable
private fun EasingPicker(
    definitions: List<EasingDefinition>,
    selectedDefinition: EasingDefinition,
    onDefinitionSelected: (EasingDefinition) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(definitions, key = { it.id }) { definition ->
            FilterChip(
                selected = selectedDefinition.id == definition.id,
                onClick = { onDefinitionSelected(definition) },
                label = {
                    Text(
                        text = definition.displayName,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                },
            )
        }
    }
}

@Composable
private fun CurvePanel(
    selectedDefinition: EasingDefinition,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        tonalElevation = 1.dp,
        color = MaterialTheme.colorScheme.surface,
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "Curve",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Medium,
                )
                Text(
                    text = selectedDefinition.family.displayName,
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
            CurveGraph(
                definition = selectedDefinition,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(184.dp),
            )
        }
    }
}

@Composable
private fun CurveGraph(
    definition: EasingDefinition,
    modifier: Modifier = Modifier,
) {
    val samples = remember(definition) { definition.samplePoints() }
    val primary = MaterialTheme.colorScheme.primary
    val secondary = MaterialTheme.colorScheme.secondary
    val grid = MaterialTheme.colorScheme.outlineVariant
    val axis = MaterialTheme.colorScheme.outline

    Canvas(modifier = modifier) {
        val minValue = min(0f, samples.minOf { it.value })
        val maxValue = max(1f, samples.maxOf { it.value })
        val valueRange = (maxValue - minValue).coerceAtLeast(0.001f)
        val horizontalPadding = 8.dp.toPx()
        val verticalPadding = 10.dp.toPx()
        val graphWidth = size.width - horizontalPadding * 2f
        val graphHeight = size.height - verticalPadding * 2f

        fun yFor(value: Float): Float {
            val normalized = (value - minValue) / valueRange
            return verticalPadding + graphHeight - normalized * graphHeight
        }

        drawLine(
            color = grid,
            start = Offset(horizontalPadding, yFor(0f)),
            end = Offset(size.width - horizontalPadding, yFor(0f)),
            strokeWidth = 1.dp.toPx(),
        )
        drawLine(
            color = grid,
            start = Offset(horizontalPadding, yFor(1f)),
            end = Offset(size.width - horizontalPadding, yFor(1f)),
            strokeWidth = 1.dp.toPx(),
        )
        drawLine(
            color = axis,
            start = Offset(horizontalPadding, verticalPadding),
            end = Offset(horizontalPadding, size.height - verticalPadding),
            strokeWidth = 1.dp.toPx(),
        )
        drawLine(
            color = axis,
            start = Offset(horizontalPadding, size.height - verticalPadding),
            end = Offset(size.width - horizontalPadding, size.height - verticalPadding),
            strokeWidth = 1.dp.toPx(),
        )

        val path = Path()
        samples.forEachIndexed { index, sample ->
            val x = horizontalPadding + sample.fraction * graphWidth
            val y = yFor(sample.value)

            if (index == 0) {
                path.moveTo(x, y)
            } else {
                path.lineTo(x, y)
            }
        }

        drawPath(
            path = path,
            color = primary,
            style = Stroke(
                width = 3.dp.toPx(),
                cap = StrokeCap.Round,
            ),
        )
        drawCircle(
            color = secondary,
            radius = 4.dp.toPx(),
            center = Offset(size.width - horizontalPadding, yFor(1f)),
        )
    }
}

@Composable
private fun PreviewPanel(
    progress: Float,
    previewProperty: PreviewProperty,
    selectedDefinition: EasingDefinition,
    durationMillis: Int,
    target: Float,
    onTargetChange: (Float) -> Unit,
    onPreviewPropertyChange: (PreviewProperty) -> Unit,
    onDurationChange: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        tonalElevation = 1.dp,
        color = MaterialTheme.colorScheme.surface,
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "Preview",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Medium,
                )
                Text(
                    text = "${(progress * 100f).roundToInt()}%",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }

            AnimationPreview(
                progress = progress,
                previewProperty = previewProperty,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(112.dp),
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                PreviewProperty.entries.forEach { property ->
                    FilterChip(
                        selected = previewProperty == property,
                        onClick = { onPreviewPropertyChange(property) },
                        label = { Text(property.label) },
                    )
                }
            }

            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = "Duration",
                        style = MaterialTheme.typography.labelLarge,
                    )
                    Text(
                        text = "${durationMillis}ms",
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
                Slider(
                    value = durationMillis.toFloat(),
                    onValueChange = {
                        onDurationChange(it.roundToInt())
                    },
                    valueRange = 200f..1600f,
                    steps = 13,
                )
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Button(
                    onClick = {
                        onTargetChange(if (target < 0.5f) 1f else 0f)
                    },
                ) {
                    Text(if (target < 0.5f) "Run" else "Reverse")
                }
                OutlinedButton(
                    onClick = { onTargetChange(0f) },
                ) {
                    Text("Reset")
                }
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = selectedDefinition.mode.displayName,
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }
    }
}

@Composable
private fun AnimationPreview(
    progress: Float,
    previewProperty: PreviewProperty,
    modifier: Modifier = Modifier,
) {
    val dotColor by animateColorAsState(
        targetValue = when (previewProperty) {
            PreviewProperty.Position -> MaterialTheme.colorScheme.primary
            PreviewProperty.Scale -> MaterialTheme.colorScheme.tertiary
            PreviewProperty.Alpha -> MaterialTheme.colorScheme.secondary
        },
        label = "previewColor",
    )
    val trackColor = MaterialTheme.colorScheme.surfaceVariant

    Canvas(modifier = modifier) {
        val sidePadding = 28.dp.toPx()
        val centerY = size.height / 2f
        val startX = sidePadding
        val endX = size.width - sidePadding
        val animatedX = when (previewProperty) {
            PreviewProperty.Position -> startX + (endX - startX) * progress
            PreviewProperty.Scale,
            PreviewProperty.Alpha,
            -> size.width / 2f
        }
        val dotScale = when (previewProperty) {
            PreviewProperty.Scale -> 0.65f + progress * 0.75f
            PreviewProperty.Position,
            PreviewProperty.Alpha,
            -> 1f
        }
        val dotAlpha = when (previewProperty) {
            PreviewProperty.Alpha -> 0.2f + progress * 0.8f
            PreviewProperty.Position,
            PreviewProperty.Scale,
            -> 1f
        }

        drawRoundRect(
            color = trackColor,
            topLeft = Offset(startX, centerY - 2.dp.toPx()),
            size = Size(endX - startX, 4.dp.toPx()),
            cornerRadius = androidx.compose.ui.geometry.CornerRadius(2.dp.toPx()),
        )
        drawCircle(
            color = dotColor.copy(alpha = dotAlpha),
            radius = 18.dp.toPx() * dotScale,
            center = Offset(animatedX, centerY),
        )
    }
}

@Composable
private fun CodePanel(
    selectedDefinition: EasingDefinition,
    durationMillis: Int,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        color = MaterialTheme.colorScheme.inverseSurface,
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Text(
                text = "Compose",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.inverseOnSurface,
                fontWeight = FontWeight.Medium,
            )
            SelectionContainer {
                Text(
                    text = selectedDefinition.composeSnippet(durationMillis),
                    style = MaterialTheme.typography.bodyMedium,
                    fontFamily = FontFamily.Monospace,
                    color = MaterialTheme.colorScheme.inverseOnSurface,
                )
            }
        }
    }
}

private fun EasingDefinition.composeSnippet(durationMillis: Int): String {
    return """
        val value by animateFloatAsState(
            targetValue = if (expanded) 1f else 0f,
            animationSpec = tween(
                durationMillis = $durationMillis,
                easing = ComposeEasings.$codeName
            ),
            label = "$id"
        )
    """.trimIndent()
}

private enum class PreviewProperty(
    val label: String,
) {
    Position("Position"),
    Scale("Scale"),
    Alpha("Alpha"),
}

@Preview(showBackground = true)
@Composable
private fun EasingDemoScreenPreview() {
    EasingTheme {
        EasingDemoScreen()
    }
}
