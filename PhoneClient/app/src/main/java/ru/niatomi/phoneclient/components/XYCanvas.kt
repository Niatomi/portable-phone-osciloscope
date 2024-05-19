package ru.niatomi.phoneclient.components

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun XYCanvas(
    x: MutableList<Int>,
    y: MutableList<Double>,
    gridYValue: Double,
    stepY: Double,
) {
    val textMeasure = rememberTextMeasurer()
    Canvas(
        modifier = Modifier
            .fillMaxSize(0.92f)
            .background(Color.Black)
            .clipToBounds(),
        onDraw = {
                y.let { _  ->
                    val grid = Path()
                    val boundGrid = Path()
                    var gridSize = 90.dp.toPx()

//                  Draw horizontal lines
                    var curPos = center.y
                    var count = 0
                    while (curPos >= 0) {
                        grid.moveTo(0f, center.y - count * gridSize)
                        grid.lineTo(size.width, center.y - count * gridSize)
                        grid.moveTo(0f, center.y + count * gridSize)
                        grid.lineTo(size.width, center.y + count * gridSize)
                        count += 1
                        curPos -= gridSize
                    }
//                  Draw vertical lines
                    curPos = center.x
                    count = 0
                    while (curPos >= 0) {
                        grid.moveTo(center.x - count * gridSize, 0f)
                        grid.lineTo(center.x - count * gridSize, size.height)
                        grid.moveTo(center.x + count * gridSize, 0f)
                        grid.lineTo(center.x + count * gridSize, size.height)
                        count += 1
                        curPos -= gridSize
                    }
                    boundGrid.moveTo(center.x, 0f)
                    boundGrid.lineTo(center.x, size.height)
                    boundGrid.moveTo(0f, center.y)
                    boundGrid.lineTo(size.width, center.y)


                    drawRect(
                        color = Color.Black,
                        topLeft = Offset(x = 0.0f, y = 0.0f),
                        size = size
                    )
                    drawPath(
                        color = Color.Gray,
                        path = grid,
                        style = Stroke(
                            width = 3.dp.toPx(),
                        )
                    )
                    drawPath(
                        color = Color.LightGray,
                        path = boundGrid,
                        style = Stroke(
                            width = 4.dp.toPx(),
                        )
                    )
                    val signalPath = Path()
                    for (i in 0..y.size - 1) {
                        val value = (-1) * y.get(i).toFloat()
                        var yValue = center.y
                        yValue += value * (center.y / (gridYValue.toFloat()))
                        signalPath.lineTo(
                            stepY.toFloat() * i,
                            yValue
                        )
                        drawCircle(
                            color = Color.Cyan,
                            center = Offset(
                                stepY.toFloat() * i,
                                yValue
                            ),
                            radius = 8.0f
                        )
                    }
                    drawPath(
                        color = Color.Green,
                        path = signalPath,
                        style = Stroke(
                            width = 3.dp.toPx(),
                        )
                    )

                    drawText(
                        textMeasurer = textMeasure,
                        text = "${gridYValue/4}V ${stepY}Hz",
                        style = TextStyle(
                            fontSize = 20.sp,
                            color = Color.Black,
                            background = Color.White
                        ),
                        topLeft = Offset(
                            x = 0.0f,
                            y = 0.0f
                        )
                    )
                }
        }
    )
}
