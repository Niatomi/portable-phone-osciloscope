package ru.niatomi.phoneclient.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ScreenSwitcher(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter) {
        Row(modifier = Modifier
            .background(Color.DarkGray)
            .fillMaxWidth()
            .padding(10.dp),
            Arrangement.SpaceBetween,
            Alignment.CenterVertically
        ) {
            Button(onClick = { navController.navigate(Screens.DEFAULT_SCREEN.route) }) {
                Text(text = "OSC")
            }
            Button(onClick = { navController.navigate(Screens.DEFAULT_SCREEN.route) }) {
                Text(text = "csv")
            }
            Button(onClick = { navController.navigate(Screens.DEFAULT_SCREEN.route) }) {
                Text(text = "xlsx")
            }
            Button(onClick = { navController.navigate(Screens.FFT.route) }) {
                Text(text = "FFT")
            }
        }
    }
}