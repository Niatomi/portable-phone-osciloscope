package ru.niatomi.phoneclient

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ru.niatomi.phoneclient.ui.theme.PhoneClientTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PhoneClientTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize().fillMaxHeight(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Niatomi", isSystemInDarkTheme());
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, theme: Boolean, modifier: Modifier = Modifier) {
    Text(
        text = "awer",
        modifier = modifier,
    )
    if (theme) {
        Text(
            text = modifier.toString(),
            modifier = modifier,
        )
    } else {
        Text(
            text = "Hello $name! Your theme is white",
            modifier = modifier,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PhoneClientTheme {
        Greeting("Android", false)
    }
}