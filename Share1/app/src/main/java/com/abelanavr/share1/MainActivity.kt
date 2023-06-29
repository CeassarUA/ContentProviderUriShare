package com.abelanavr.share1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import java.io.File
import java.io.FileWriter


class MainActivity : AppCompatActivity() {
    companion object {
        const val providerAuth = "com.abelanavr.share1.fileprovider"
    }

    fun saveFileInAppDirectory(name: String): File {
        val file = createFileInExternalStorage(
            baseContext,
            name
        )
        if (!file.exists()) {
            file.createNewFile()
        }
        val text = "some generated data"
        val writer = FileWriter(file)
        writer.append(text)
        writer.flush()
        writer.close()
        return file
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etName = findViewById<EditText>(R.id.et_name)
        val etPath = findViewById<EditText>(R.id.et_path)

        findViewById<View>(R.id.bt_create)
            .setOnClickListener {
                val file =
                    saveFileInAppDirectory(etName.text.toString()) //generation of file for test can be used apk file


                val uri = FileProvider.getUriForFile(
                    this@MainActivity,
                    providerAuth,
                    file
                )
                Log.d("path", uri.toString())
                Log.d("path", file.absolutePath)

                baseContext.grantUriPermission(
                    "com.abelanavr.share2",// real app package name
                    uri,
                     Intent.FLAG_GRANT_READ_URI_PERMISSION
                )
                etPath.setText(uri.toString())
            }


    }
}