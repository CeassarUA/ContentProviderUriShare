package com.abelanavr.share1

import android.content.Context
import android.os.Environment
import java.io.File

fun createFileInStorage(context: Context, fileName: String): File {
    val timeStamp: String = System.currentTimeMillis().toString()
    val name = if (fileName.isBlank()) timeStamp else fileName
    return File(getAppFilesDir(context), name)
}

fun createFileInExternalStorage(context: Context, fileName: String): File {
    val timeStamp: String = System.currentTimeMillis().toString()
    val name = if (fileName.isBlank()) timeStamp else fileName
    return File(getAppExternalFilesDir(context), name)
}

fun getAppFilesDir(context: Context): File? {
    val file = context.filesDir
    if (file != null && !file.exists()) {
        file.mkdirs()
    }
    return file
}

private fun getAppExternalFilesDir(context: Context): File? {
    val file = context.getExternalFilesDir("apk")
    if (file != null && !file.exists()) {
        file.mkdirs()
    }
    return file
}