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

    /**
     * fetching all notes sorted by date
     */
    @Query("SELECT * from NoteData Order by date DESC")
    fun getAllNotes(): LiveData<List<NoteData>>

    /**
     * inserting a new or updating existing note
     */
    @Insert(onConflict = REPLACE)
    fun insertNoteToDb(noteData: NoteData)

    /**
     * deleting all note
     */
    @Delete
    fun deleteAllNotes(noteData: NoteData)

    /**
     * Deleting a note by noteId
     */
    @Query("DELETE FROM NoteData WHERE noteId=:noteId")
    fun deleteNoteWithId(noteId:String)

    /**
     * fetching a note by notId
     */
    @Query("SELECT * from NoteData WHERE noteId= :noteId" )
    fun getNoteWithId(noteId:String): LiveData<NoteData>

    /**
     * Updating only title By note id
     */
    @Query("UPDATE NoteData SET noteTitle=:title WHERE noteId = :noteId")
    fun updateTitleForNoteId(title:String,noteId:String)

}