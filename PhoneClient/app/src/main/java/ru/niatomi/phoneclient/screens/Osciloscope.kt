package ru.niatomi.phoneclient.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ru.niatomi.phoneclient.components.XYCanvas
import ru.niatomi.phoneclient.utils.RingBuffer
import java.time.Instant

@Composable
fun Oscilloscope(
    ringBuffer: MutableState<RingBuffer<Pair<Instant, Double>>>
) {
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
                y = ringBuffer.value.map { it.second }.toMutableList(),
                gridYValue = 2.0,
                stepY = 50.0
            )
            Row(modifier = Modifier
                .background(Color.DarkGray)
                .fillMaxWidth()
                .padding(10.dp),
                Arrangement.SpaceBetween,
                Alignment.CenterVertically
            ) {
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "+")
                }
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "-")
                }
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "*")
                }
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "/")
                }
            }
        }
    }
}