package com.pawanjeswani.todoapp.datarepo

import android.os.Handler
import android.os.HandlerThread
import androidx.lifecycle.LiveData
import com.pawanjeswani.todoapp.database.AppDatabase
import com.pawanjeswani.todoapp.database.DatabaseCreator
import com.pawanjeswani.todoapp.database.interfaces.DbQueryListener
import com.pawanjeswani.todoapp.model.dbtables.NoteData


class LocalDataRepository {
    private var appDatabase: AppDatabase? = null


    private constructor() {
        appDatabase = DatabaseCreator.getInstance()!!.database;
    }


    fun insertNote(item: NoteData, listener: DbQueryListener) {
        val mHandlerThread = HandlerThread("Handler")
        mHandlerThread.start()
        val handler = Handler(mHandlerThread.looper)
        val runnable = {
            appDatabase!!.todoDao.insertNoteToDb(item)
            listener.onSuccess()
        }
        handler.post(runnable)
    }


    fun deleteAllEntries(NoteData: NoteData){
        appDatabase!!.todoDao.deleteAllNotes(NoteData)
    }

    fun getAllNotes(): LiveData<List<NoteData>> {
        return appDatabase!!.todoDao.getAllNotes()
    }

    fun getNoteWithId(noteId:String):LiveData<NoteData>{
        return appDatabase!!.todoDao.getNoteWithId(noteId = noteId)
    }

    fun deleteNoteWithId(noteId:String){
        return appDatabase!!.todoDao.deleteNoteWithId(noteId = noteId)
    }

    companion object {
        private var localDataRepository: LocalDataRepository? = null

        fun instance(): LocalDataRepository? {
            if (localDataRepository == null) {
                localDataRepository = LocalDataRepository()
            }
            return localDataRepository
        }
    }
}