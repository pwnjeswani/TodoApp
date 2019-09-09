package com.pawanjeswani.todoapp.view.activity

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.pawanjeswani.todoapp.R
import com.pawanjeswani.todoapp.database.interfaces.DbQueryListener
import com.pawanjeswani.todoapp.model.dbtables.NoteData
import com.pawanjeswani.todoapp.util.Constants
import com.pawanjeswani.todoapp.util.Constants.Companion.NOTE_DATA
import com.pawanjeswani.todoapp.viewmodel.TodoViewModel
import kotlinx.android.synthetic.main.activity_edit_note.*
import java.text.SimpleDateFormat
import java.util.*


class EditNoteActivity : AppCompatActivity() {

    lateinit var todoViewModel: TodoViewModel
    private var isNewNote = true
    lateinit var noteData: NoteData
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_note)
        todoViewModel = ViewModelProviders.of(this).get(TodoViewModel::class.java)
        //setting back btn click
        iv_back.setOnClickListener {
            onBackPressed()
        }
        getIntentData()
        if (!isNewNote) {
            //Editing the note
            tv_title.text = getString(R.string.edit_note)
            et_content.setText(noteData.noteContent)
        }

        btn_submit.setOnClickListener {
            if (et_content.text.isEmpty()) {
                //user has not entered anyrhing hence showing error
                setError()
            } else {
                //user has entered some text hence saving it
                if (isNewNote) {
                    saveNote()
                } else {
                    editNote()
                }
            }
        }
    }

    private fun getIntentData() {
        if (intent != null && intent.hasExtra(NOTE_DATA)) {
            noteData = intent.getSerializableExtra(NOTE_DATA) as NoteData
            isNewNote = false
        } else {
            noteData = NoteData()
        }
    }

    private fun saveNote() {
        var date = Date()
        date.time = System.currentTimeMillis()
        val df = SimpleDateFormat("MM-dd-yyyy", Locale.ENGLISH)
        noteData.date = df.format(date)
        noteData.noteId = UUID.randomUUID().toString()
        noteData.noteContent = et_content.text.toString()
        todoViewModel.saveNote(noteData, DbQueryListener {
            //successfully note is saved, hence sending result ok to main screen
            setResult(Activity.RESULT_OK)
            finish()
        })
    }

    private fun editNote(){
        var noteData = NoteData()
        var date = Date()
        date.time = System.currentTimeMillis()
        val df = SimpleDateFormat("MM-dd-yyyy", Locale.ENGLISH)
        noteData.date = df.format(date)
        noteData.noteId = this.noteData.noteId
        noteData.noteContent = et_content.text.toString()
        todoViewModel.saveNote(noteData, DbQueryListener {
            //successfully note is edited, hence sending result ok to main screen
            setResult(Activity.RESULT_OK)
            finish()
        })
    }
    private fun setError() {
        et_content.error = getString(R.string.note_error_txt)
        et_content.requestFocus()
    }

}
