package com.marcus.materialescolar

import android.app.Application
import com.marcus.materialescolar.db.AppDatabase

/**
 * Created by Marcus on 30-Jan-18.
 *
 */
class App : Application() {

    val appExecutors: AppExecutors = AppExecutors()

    fun getDatabase(): AppDatabase {
        return AppDatabase.getInstance(this)
    }

    fun getRepository(): DataRepository {
        return DataRepository.getInstance(getDatabase())
    }

    override fun onTerminate() {
        super.onTerminate()
        if (!appExecutors.diskIO.isShutdown)
            appExecutors.diskIO.shutdown()
    }
}