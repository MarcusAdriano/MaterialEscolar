package com.marcus.materialescolar

import android.app.Application
import com.marcus.materialescolar.db.AppDatabaseManager

/**
 * Created by Marcus on 30-Jan-18.
 *
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        AppDatabaseManager.init(applicationContext)
    }

    override fun onTerminate() {
        super.onTerminate()
        AppDatabaseManager.instance.close()
    }


}