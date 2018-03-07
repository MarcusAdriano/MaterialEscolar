package com.marcus.materialescolar

import java.util.concurrent.Executors
import java.util.concurrent.ExecutorService

/**
 * Created by Marcus on 05-Feb-18.
 *
 */
class AppExecutors(val diskIO: ExecutorService) {

    constructor() : this(Executors.newFixedThreadPool(2))

    companion object {
        val TASK_COMPLETE = 1
        val TASK_ERROR = 2
    }
}
