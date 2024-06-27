package ru.niatomi.phoneclient

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.hoho.android.usbserial.driver.UsbSerialPort
import kotlinx.coroutines.delay
import ru.niatomi.phoneclient.navigation.Navigation
import ru.niatomi.phoneclient.utils.RingBuffer
import java.io.IOException
import java.time.Instant
import kotlin.math.PI

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Entrypoint(
) {
    val recentInstantTimeTake = remember {
        mutableStateOf(Instant.now())
    }
    val rf = remember {
        mutableStateOf(RingBuffer<Pair<Instant, Double>>(bufferSize = 52))
    }
    val currentFrame = remember {
        mutableStateOf(ByteArray(size = 8))
    }

    LaunchedEffect(key1 = currentFrame) {
        var x = 0.0
        while (true) {
            recentInstantTimeTake.value = Instant.now()
            val clone = rf.value.clone()
            var value = Math.cos(x * PI)
            clone.add(Pair(recentInstantTimeTake.value, value))
            rf.value = clone
            val buffer = ByteArray(size = 8)
            delay(50)
            x += 0.1
//            Log.d("SERIAL", buffer.contentToString())
//            Log.d("SERIAL-PARSE", FrameParser.parse(buffer).toString())
//            var value = FrameParser.parse(buffer)
//            if (value == 0.0) {
//                recentInstantTimeTake.value = Instant.now()
//                val clone = rf.value.clone()
//                clone.add(Pair(recentInstantTimeTake.value, FrameParser.parse(currentFrame.value)))
//                rf.value = clone
//            } else {
//                currentFrame.value = buffer
//                recentInstantTimeTake.value = Instant.now()
//                val clone = rf.value.clone()
//                clone.add(Pair(recentInstantTimeTake.value, FrameParser.parse(buffer)))
//                rf.value = clone
//            }
        }
    }
    Navigation(ringBuffer = rf)
}

