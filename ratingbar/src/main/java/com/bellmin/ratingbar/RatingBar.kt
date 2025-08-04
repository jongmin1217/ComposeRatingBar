package com.bellmin.ratingbar

import android.view.MotionEvent
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.ceil

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun RatingBar(
    rating: Float,
    onRatingChange: (Float) -> Unit,
    modifier: Modifier = Modifier,
    step: Float = 0.5f,
    itemCount: Int = 5,
    enabled: Boolean = true,
    painter: Painter,
    itemWidth: Dp = 24.dp,
    itemHeight: Dp = 24.dp,
    itemSpacing: Dp = 4.dp,
    selectedColor: Color = Color.Yellow,
    unselectedColor: Color = Color.Gray,
) {

    val normalizedRating = rating.coerceIn(0f, itemCount.toFloat())
    var totalWidthPx by remember { mutableFloatStateOf(0f) }

    Row(
        modifier = modifier
            .pointerInteropFilter{ event ->
                if (!enabled) return@pointerInteropFilter false
                when (event.action) {
                    MotionEvent.ACTION_DOWN, MotionEvent.ACTION_MOVE -> {
                        val x  = event.x
                        val value = roundToStep((x*10)/totalWidthPx/(10f/itemCount), step)
                        when{
                            value <= 0 -> onRatingChange(0f)
                            value<=itemCount -> onRatingChange(value)
                        }
                        true
                    }
                    else -> false
                }
            }.onGloballyPositioned{
                totalWidthPx = it.size.width.toFloat()
            },
        horizontalArrangement = Arrangement.spacedBy(itemSpacing),
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (i in 0 until itemCount) {
            val filledFraction = (normalizedRating - i).coerceIn(0f, 1f)

            Box(
                modifier = Modifier.width(itemWidth).height(itemHeight),
                contentAlignment = Alignment.Center
            ) {
                val unselectedAnimColor = animateColorAsState(unselectedColor).value
                Icon(
                    painter = painter,
                    contentDescription = null,
                    tint = unselectedAnimColor,
                    modifier = Modifier.matchParentSize()
                )

                if (filledFraction > 0f) {
                    val selectedAnimColor = animateColorAsState(selectedColor).value

                    Icon(
                        painter = painter,
                        contentDescription = null,
                        tint = selectedAnimColor,
                        modifier = Modifier
                            .matchParentSize()
                            .drawWithContent {
                                val width = size.width * filledFraction
                                clipRect(left = 0f, top = 0f, right = width, bottom = size.height) {
                                    this@drawWithContent.drawContent()
                                }
                            }
                    )
                }
            }

        }
    }
}

fun roundToStep(value: Float, step: Float): Float {
    if (step <= 0f) return value
    val rounded = ceil(value / step) * step
    val decimals = step.toString().substringAfter(".", "").length
    return "%.${decimals}f".format(rounded).toFloat()
}
