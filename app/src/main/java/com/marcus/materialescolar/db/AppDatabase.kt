package com.marcus.materialescolar.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.marcus.materialescolar.db.dao.MaterialDao
import com.marcus.materialescolar.model.Material

/**
 * Created by Marcus on 31-Jan-18.
 *
 */
@Database(entities = arrayOf(Material::class), version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun materialDao(): MaterialDao

}