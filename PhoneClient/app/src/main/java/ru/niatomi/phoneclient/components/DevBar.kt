package ru.niatomi.phoneclient.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import ru.niatomi.phoneclient.utils.FrameParser
import java.time.Instant

@Composable
fun DevBar(
    instantTime: MutableState<Instant>,
    byteFrame: MutableState<ByteArray>,
) {
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .background(Color.DarkGray)
                .padding(5.dp),
            Arrangement.SpaceBetween
        ) {
            ProvideTextStyle(TextStyle(color = Color.White)) {
                Text(text = "${instantTime.value}")
                Text(text = "${byteFrame.value.contentToString()}")
                Text(text = "%.4f".format(FrameParser.parse(byteFrame.value)))
            }
        }
    }
}