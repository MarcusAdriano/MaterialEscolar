package com.marcus.materialescolar.db

import android.arch.persistence.room.Room
import android.content.Context
import com.marcus.materialescolar.Logger

/**
 * Created by Marcus on 31-Jan-18.
 *
 */
class AppDatabaseManager private constructor() {

    init {
        Logger.debug("Database manager created!")
    }

    val databaseName = "mat-esc.db"
    var closed = false
        private set

    lateinit var database : AppDatabase
        get
        private set

    @Synchronized fun close() {
        if (closed) return
        database.close()
        closed = true
    }

    companion object {
        lateinit var instance : AppDatabaseManager
            get
            private set

        @Synchronized fun init(context: Context) {
            instance = AppDatabaseManager()
            instance.database = Room
                    .databaseBuilder(context, AppDatabase::class.java, instance.databaseName)
                    .build()

        }
    }
}