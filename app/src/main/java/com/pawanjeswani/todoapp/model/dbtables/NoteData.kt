package com.pawanjeswani.todoapp.model.dbtables

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class NoteData{
    @PrimaryKey
    var noteId:String = ""
    var noteContent : String?=null
    var noteTitle : String=""
    var date : String?=null
    var uploaded: Boolean = false
}