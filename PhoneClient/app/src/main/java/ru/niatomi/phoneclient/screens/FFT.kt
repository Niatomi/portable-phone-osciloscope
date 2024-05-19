package ru.niatomi.phoneclient.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ru.niatomi.phoneclient.components.XYCanvas
import ru.niatomi.phoneclient.utils.Cexp
import ru.niatomi.phoneclient.utils.RingBuffer
import ru.niatomi.phoneclient.utils.recursiveFFT
import java.time.Instant
import kotlin.math.abs

@Composable
fun FFT(
    ringBuffer: MutableState<RingBuffer<Pair<Instant, Double>>>
) {

    val fft = recursiveFFT(ringBuffer.value.map { Cexp(it.second) }.toTypedArray())
    val pointsData: MutableList<Double> = mutableListOf()
    for ((index, item)in fft.withIndex()) {
        pointsData.add(abs(item.r))
    }

    Box(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(1f)
        .padding(10.dp)) {

        Column(modifier = Modifier
            .background(Color.DarkGray)
            .fillMaxSize()
            .padding(2.dp),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            XYCanvas(
                x = ringBuffer.value.map { it.first.nano }.toMutableList(),
                y = pointsData,
                gridYValue = 10.0,
                stepY = 18.0
            )
        }
    }
}