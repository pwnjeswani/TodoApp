package com.pawanjeswani.todoapp.model.dbtables

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
class NoteData: Serializable{
    @PrimaryKey
    var noteId:String = ""
    var noteContent : String?=null
    var noteTitle : String=""
    var date : String?=null
}