package com.putri.widya

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.io.*

// pada class MainActivity memuat bundle untuk format semua source kode pada aplikasi.
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // script dibawah digunakan untuk proses pengisian data,
        // pada variabel fileName menggunakan EditText dengan idnya "editFile",
        // pada variabel fileData menggunakan EditText dengan idnya "editData".
        val fileName = findViewById<EditText>(R.id.editFile)
        val fileData = findViewById<EditText>(R.id.editData)

        // script dibawah digunakan untuk proses menyimpan dan menampilkan data,
        // pada variabel btnSave menggunakan Button dengan idnya "btnSave",
        // pada variabel btnView menggunakan Button dengan idnya "btnView".
        val btnSave = findViewById<Button>(R.id.btnSave)
        val btnView = findViewById<Button>(R.id.btnView)

        // pada button save saat di klik akan menyimpan file name dengan tipe data
        // string dan file data dengan tipe data string.
        btnSave.setOnClickListener(View.OnClickListener {
            val file:String = fileName.text.toString()
            val data:String = fileData.text.toString()
            val fileOutputStream:FileOutputStream
            try {
                fileOutputStream = openFileOutput(file, Context.MODE_PRIVATE)
                fileOutputStream.write(data.toByteArray())
            } catch (e: FileNotFoundException){
                e.printStackTrace()
            }catch (e: NumberFormatException){
                e.printStackTrace()
            }catch (e: IOException){
                e.printStackTrace()
            }catch (e: Exception){
                e.printStackTrace()
            }
            Toast.makeText(applicationContext,"data save",Toast.LENGTH_LONG).show()
            fileName.text.clear()
            fileData.text.clear()
        })

        // pada button save saat di klik akan menampilkan isi dari data file name
        // jika file name yang dimasukkan tidak ada maka akan terjadi pesan error
        // yaitu "file name cannot be blank".
        btnView.setOnClickListener(View.OnClickListener {
            val filename = fileName.text.toString()
            if(filename.toString()!=null && filename.toString().trim()!=""){
                var fileInputStream: FileInputStream? = null
                fileInputStream = openFileInput(filename)
                var inputStreamReader: InputStreamReader = InputStreamReader(fileInputStream)
                val bufferedReader: BufferedReader = BufferedReader(inputStreamReader)
                val stringBuilder: StringBuilder = StringBuilder()
                var text: String? = null
                while ({ text = bufferedReader.readLine(); text }() != null) {
                    stringBuilder.append(text)
                }
                //Displaying data on EditText
                fileData.setText(stringBuilder.toString()).toString()
            }else{
                Toast.makeText(applicationContext,"file name cannot be blank",Toast.LENGTH_LONG).show()
            }
        })
    }
}