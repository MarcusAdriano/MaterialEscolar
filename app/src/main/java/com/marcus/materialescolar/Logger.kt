package com.marcus.materialescolar

import android.util.Log

/**
 * Created by Marcus on 30-Jan-18.
 *
 */
object Logger {

    private val TAG = "MyApp"

    /**
     * Print a message in Logcat using mode debug
     * @param message print it in Logcat
     */
    fun debug(message: String) {
        Log.d(TAG, message)
    }

    /**
     * Print a message in Logcat using mode debug
     * @param exception retrieve exception message to output it in logcat
     */
    fun debug(exception: Exception) {
        this.debug(exception.message ?: "Null Exception")
    }
}