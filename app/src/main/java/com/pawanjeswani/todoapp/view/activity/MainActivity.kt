package com.pawanjeswani.todoapp.view.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.pawanjeswani.todoapp.R
import com.pawanjeswani.todoapp.model.dbtables.NoteData
import com.pawanjeswani.todoapp.util.Constants.Companion.REQUEST_CREATE_NEW
import com.pawanjeswani.todoapp.util.Constants.Companion.REQUEST_EDIT
import com.pawanjeswani.todoapp.view.adapter.NotesAdapter
import com.pawanjeswani.todoapp.viewmodel.TodoViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var todoViewModel: TodoViewModel
    var notesAdapter: NotesAdapter?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        todoViewModel = ViewModelProviders.of(this).get(TodoViewModel::class.java)
        notesAdapter = NotesAdapter(this)
        setupRecyclerView()
        fetchNotes()
        fab_add_note.setOnClickListener {
            openNewNoteAct()
        }
    }

    private fun setupRecyclerView() {
        var linearLayoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        linearLayoutManager.orientation = RecyclerView.VERTICAL
        rv_notes.layoutManager = linearLayoutManager
        rv_notes.adapter = notesAdapter
    }

    private fun openNewNoteAct() {
        var intent = Intent(this, EditNoteActivity::class.java)
        startActivityForResult(intent, REQUEST_CREATE_NEW)
    }

    private fun fetchNotes() {
        todoViewModel.fetchNotes().observe(this,androidx.lifecycle.Observer {
            if(it.isEmpty() || it !=null){
                //got saved notes hence passing it to adapter
                passToAdapter(it)
//                Toast.makeText(this,"${it.size}", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun passToAdapter(noteList: List<NoteData>?) {
        notesAdapter!!.setNotes(noteList as ArrayList<NoteData>)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                REQUEST_CREATE_NEW -> {
                    //create the note hence updating the rv
                    fetchNotes()
                }
                REQUEST_EDIT -> {
                    //editted successfully hence updating the rv
                    fetchNotes()
                }
            }
        }
    }
}
