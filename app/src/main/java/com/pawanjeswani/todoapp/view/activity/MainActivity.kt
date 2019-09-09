package com.pawanjeswani.todoapp.view.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pawanjeswani.todoapp.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val REQUEST_CREATE_NEW = 1
    val REQUEST_EDIT = 2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fab_add_note.setOnClickListener {
            openNewNoteAct()
        }
    }

    private fun openNewNoteAct() {
        var intent = Intent(this, EditNoteActivity::class.java)
        startActivityForResult(intent, REQUEST_CREATE_NEW)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                REQUEST_CREATE_NEW -> {
                    //create the note hence updating the rv
                }
                REQUEST_EDIT -> {
                    //editted successfully hence updating the rv
                }
            }
        }
    }
}
