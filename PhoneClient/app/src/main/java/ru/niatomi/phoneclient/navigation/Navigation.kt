package ru.niatomi.phoneclient.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.niatomi.phoneclient.screens.FFT
import ru.niatomi.phoneclient.screens.Oscilloscope
import ru.niatomi.phoneclient.utils.RingBuffer
import java.time.Instant


@Composable
fun Navigation(ringBuffer: MutableState<RingBuffer<Pair<Instant, Double>>>) {
    val navController = rememberNavController()
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(modifier = Modifier
            .fillMaxHeight(0.92f)
            .fillMaxWidth()) {
            NavHost(navController = navController, startDestination = Screens.DEFAULT_SCREEN.route) {
                composable(route = Screens.DEFAULT_SCREEN.route) { Oscilloscope(ringBuffer = ringBuffer) }
                composable(route = Screens.FFT.route) { FFT(ringBuffer = ringBuffer) }
            }
        }
        ScreenSwitcher(navController)
    }
}