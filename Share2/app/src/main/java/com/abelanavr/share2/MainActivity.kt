package com.abelanavr.share2

import android.content.Context
import android.os.Bundle
import android.os.FileUtils
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toFile
import androidx.core.net.toUri
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.FileWriter
import java.io.IOException
import java.io.InputStream


class MainActivity : AppCompatActivity() {

    fun generateDumyFile(context: Context?, sFileName: String?, sBody: String?): String {
        val root = File(sFileName)
        val writer = FileWriter(root)
        writer.append(sBody)
        writer.flush()
        writer.close()
        Toast.makeText(context, "Saved" + root.absolutePath, Toast.LENGTH_SHORT).show()
        return root.absolutePath
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fun Context.internalFilesPath() = "${filesDir.path}${File.separator}"

        @Throws(IOException::class)
        fun copy(`in` : InputStream, dst: File?) {

                FileOutputStream(dst).use { out ->
                    // Transfer bytes from in to out
                    val buf = ByteArray(1024)
                    var len: Int
                    while (`in`.read(buf).also { len = it } > 0) {
                        out.write(buf, 0, len)
                    }
                    Log.d("file", "copied")
                }

        }

        val etName = findViewById<EditText>(R.id.et_name)
        etName.setText("content://com.abelanavr.share1.fileprovider/apk/filename.apk")
        findViewById<View>(R.id.bt_create)
            .setOnClickListener {
                copy(
                    contentResolver.openInputStream(etName.text.toString().toUri())!!,
                    File(internalFilesPath() + "file.txt")
                )
            }


    }
}