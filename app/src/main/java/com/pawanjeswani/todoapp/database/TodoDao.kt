package com.pawanjeswani.todoapp.database


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.pawanjeswani.todoapp.model.dbtables.NoteData

@Dao
interface TodoDao {

    @Query("SELECT * from NoteData")
    fun getAllNotes(): LiveData<List<NoteData>>

    @Insert(onConflict = REPLACE)
    fun insertNoteToDb(noteData: NoteData)

    @Delete
    fun deleteAllNotes(noteData: NoteData)

    @Query("DELETE FROM NoteData WHERE noteId=:noteId")
    fun deleteNoteWithId(noteId:String)

    @Query("SELECT * from NoteData WHERE noteId= :noteId" )
    fun getNoteWithId(noteId:String): LiveData<NoteData>


}