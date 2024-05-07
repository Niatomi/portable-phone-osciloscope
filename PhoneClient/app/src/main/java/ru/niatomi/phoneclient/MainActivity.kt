package ru.niatomi.phoneclient

import android.content.Context
import android.hardware.usb.UsbManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.hoho.android.usbserial.driver.UsbSerialPort
import com.hoho.android.usbserial.driver.UsbSerialProber
import kotlinx.coroutines.delay
import ru.niatomi.phoneclient.utils.RingBuffer


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getSystemService(Context.USB_SERVICE)
        val manager = getSystemService(Context.USB_SERVICE) as UsbManager
        val availableDrivers = UsbSerialProber.getDefaultProber().findAllDrivers(manager)
        val driver = availableDrivers[0]
        val connection = manager.openDevice(driver.device)
            ?:
            return

        val port = driver.ports.get(0)
        port.open(connection)
        port.setParameters(115200, 8, UsbSerialPort.STOPBITS_1, UsbSerialPort.PARITY_NONE);

        setContent {
            Entrypoint(port = port)
        }
    }

}



@Composable
fun Entrypoint(
    port: UsbSerialPort,
) {

    val currentFrame = remember {
        mutableStateOf(ByteArray(size = 8))
    }
    val rf = remember {
        mutableStateOf(RingBuffer<ByteArray>(bufferSize = 30))
    }

    LaunchedEffect(true) {
        while (true) {
            delay(90)
            val buffer = ByteArray(size = 8)
            port.read(buffer, 1);
            currentFrame.value = buffer
            rf.value.add(buffer)
        }
    }
    Column {
        Text(text = "${currentFrame.value.contentToString()}")
        Text(text = "${rf.value.size}")
        for (frame in rf.value) {
            Text(text = "${frame.contentToString()}")
        }
    }
}

