package com.pawanjeswani.todoapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pawanjeswani.todoapp.database.interfaces.DbQueryListener
import com.pawanjeswani.todoapp.datarepo.LocalDataRepository
import com.pawanjeswani.todoapp.model.dbtables.NoteData


class TodoViewModel : ViewModel() {

    var dataRepository = LocalDataRepository.instance()

    fun saveNote(noteData: NoteData, listener: DbQueryListener) {
        dataRepository!!.insertNote(noteData, listener)
    }

    fun fetchNotes(): LiveData<List<NoteData>> {
        val conversation = MutableLiveData<List<NoteData>>()
        dataRepository!!.getAllNotes().observeForever { response ->
            conversation.value = response
        }
        return conversation
    }

    fun fetchNoteByNoteId(noteId: String): LiveData<NoteData> {
        val conversation = MutableLiveData<NoteData>()
        dataRepository!!.getNoteWithId(noteId).observeForever { response ->
            conversation.value = response
        }
        return conversation
    }

    fun deleteNoteByNoteId(noteId: String,listener: DbQueryListener) {
        dataRepository!!.deleteNoteWithId(noteId,listener)
    }

    fun deleteNotes() {
        dataRepository!!.deleteAllEntries()
    }
}