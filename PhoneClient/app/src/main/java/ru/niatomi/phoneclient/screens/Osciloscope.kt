package ru.niatomi.phoneclient.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import ru.niatomi.phoneclient.utils.RingBuffer
import java.time.Instant

@Composable
fun Oscilloscope(
    ringBuffer: MutableState<RingBuffer<Pair<Instant, Double>>>
) {
    Box(modifier = Modifier.fillMaxWidth().fillMaxHeight(0.97f).padding(10.dp)) {
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black),
            onDraw = {
                    ringBuffer.let { buffer  ->
                        drawRect(
                            color = Color.Black,
                            topLeft = Offset(x = 0.0f, y = 0.0f),
                            size = size
                        )
                        for ((index, frame) in buffer.value.withIndex()) {
                            drawRect(
                                color = Color.White,
                                topLeft = Offset(size.width - 40 * index, center.y + 100 * buffer.value.get(index).second.toFloat()),
                                size = Size(10.0f, 10.0f)
                            )
                        }
                    }
            }
        )
    }
}