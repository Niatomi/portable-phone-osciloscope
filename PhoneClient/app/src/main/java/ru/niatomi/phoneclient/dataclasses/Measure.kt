package ru.niatomi.phoneclient.dataclasses

import java.time.Instant

data class Measure(
    val time: Instant,
    val value: Double
)
