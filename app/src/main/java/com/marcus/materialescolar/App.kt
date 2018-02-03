package com.marcus.materialescolar

import android.app.Application
import com.marcus.materialescolar.db.AppDatabase



/**
 * Created by Marcus on 30-Jan-18.
 *
 */
class App : Application() {

    fun getDatabase(): AppDatabase {
        return AppDatabase.getInstance(this)
    }

    fun getRepository(): DataRepository {
        return DataRepository.getInstance(getDatabase())
    }

}