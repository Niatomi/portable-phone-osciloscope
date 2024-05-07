package ru.niatomi.phoneclient

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.hoho.android.usbserial.driver.UsbSerialPort
import kotlinx.coroutines.delay
import ru.niatomi.phoneclient.components.DevBar
import ru.niatomi.phoneclient.utils.FrameParser
import ru.niatomi.phoneclient.utils.RingBuffer
import java.time.Instant

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Entrypoint(
    port: UsbSerialPort,
) {
    val recentInstantTimeTake = remember {
        mutableStateOf(Instant.now())
    }
    val rf = remember {
        mutableStateOf(RingBuffer<Pair<Instant, Double>>(bufferSize = 30))
    }
    val currentFrame = remember {
        mutableStateOf(ByteArray(size = 8))
    }

    LaunchedEffect(true) {
        while (true) {
            delay(90)
            val buffer = ByteArray(size = 8)
            port.read(buffer, 1);
            Log.d("SERIAL", buffer.contentToString())
            Log.d("SERIAL-PARSE", FrameParser.parse(buffer).toString())
            currentFrame.value = buffer
            recentInstantTimeTake.value = Instant.now()
            rf.value.add(Pair(recentInstantTimeTake.value, FrameParser.parse(buffer)))
        }
    }
    Column {
       DevBar(
            instantTime = recentInstantTimeTake,
            byteFrame = currentFrame)
    }
}

