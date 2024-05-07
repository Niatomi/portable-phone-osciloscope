package ru.niatomi.phoneclient.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState

@Composable
fun XYCanvas(
    x: MutableState<Array<Any>>,
    y: MutableState<Array<Double>>
) {
    Text(text = "XYCanvas")

}
