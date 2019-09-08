package com.pawanjeswani.todoapp

import android.app.Application
import com.pawanjeswani.todoapp.database.DatabaseCreator

class TodoApp : Application() {


    override fun onCreate() {

        super.onCreate()
        app = this
//        DataRepository.instance()
        DatabaseCreator.getInstance()!!.createDb(this)
    }

    companion object {

        private var app: TodoApp? = null

        fun instance(): TodoApp? {
            return app
        }
    }
}
