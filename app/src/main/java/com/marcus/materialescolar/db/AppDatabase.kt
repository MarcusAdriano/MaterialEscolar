package com.marcus.materialescolar.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.marcus.materialescolar.db.dao.MaterialDao
import com.marcus.materialescolar.model.Material
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.persistence.db.SupportSQLiteDatabase


/**
 * Created by Marcus on 31-Jan-18.
 *
 */
@Database(entities = arrayOf(Material::class), version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun materialDao(): MaterialDao

    private val DATABASE_NAME = "material-db"
    private var databaseCreated = MutableLiveData<Boolean>()

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context,
                    AppDatabase::class.java, "material-db")
                    .build()
        }
    }

    private fun updateDatabaseCreated(context: Context) {
        if (context.getDatabasePath(DATABASE_NAME).exists()) {
            setDatabaseCreated()
        }
    }

    private fun setDatabaseCreated() {
        databaseCreated.postValue(true)
    }

    fun getDatabaseCreated(): LiveData<Boolean> {
        return databaseCreated
    }

}