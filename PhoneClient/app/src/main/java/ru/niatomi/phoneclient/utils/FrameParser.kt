package ru.niatomi.phoneclient.utils

class FrameParser {
    companion object {
        fun parse(frame: ByteArray): Double {
            val negativeMultiplier = if (frame[3].toInt() == 1) -1 else 1
            val integerPart = frame[4]
            val firstTwoDecimals = frame[5] * 0.01
            val secondTwoDecimals = frame[6] * 0.0001
            return negativeMultiplier * (integerPart + firstTwoDecimals + secondTwoDecimals)
        }
    }
}