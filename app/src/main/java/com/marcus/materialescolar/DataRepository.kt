package com.marcus.materialescolar

import android.arch.lifecycle.MediatorLiveData
import com.marcus.materialescolar.db.AppDatabase
import com.marcus.materialescolar.model.Material
import android.arch.lifecycle.LiveData

/**
 * Created by Marcus on 02-Feb-18.
 *
 */
class DataRepository private constructor(private var appDatabase: AppDatabase) {

    private var mObservableMaterial: LiveData<List<Material>> = appDatabase.materialDao().getAll()

    companion object {
        private var INSTANCE: DataRepository? = null

        fun getInstance(appDatabase: AppDatabase) : DataRepository =
            INSTANCE ?: synchronized(DataRepository::class, {
                INSTANCE ?: DataRepository(appDatabase).also {
                    INSTANCE = it
                }
            })
    }

    fun getMateriais(): LiveData<List<Material>> {
        return mObservableMaterial
    }

    fun getMaterial(materialId: Long): LiveData<Material> {
        return appDatabase.materialDao().getById(materialId)
    }

}
