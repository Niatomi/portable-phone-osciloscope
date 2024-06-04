package ru.niatomi.phoneclient.utils

import android.content.Context
import android.os.Environment
import ru.niatomi.phoneclient.dataclasses.Measure
import java.io.File
import java.io.OutputStream

fun OutputStream.writeCsv(context: Context, listOfData: List<Measure>) {
    //get data create directory

    val writer = bufferedWriter()
    writer.write(""""Nanoseconds", "Value"""")
    writer.newLine()
    listOfData.forEach {
        writer.write("${it.time}, ${it.value}")
        writer.newLine()
    }
    writer.flush()
    writer.close()
}