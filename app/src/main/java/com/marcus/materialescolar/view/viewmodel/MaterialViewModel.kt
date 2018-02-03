package com.marcus.materialescolar.view.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.databinding.ObservableField
import com.marcus.materialescolar.DataRepository
import com.marcus.materialescolar.model.Material
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.marcus.materialescolar.App


/**
 * Created by Marcus on 02-Feb-18.
 *
 */
class MaterialViewModel(application: Application, private val dataRepository: DataRepository, id: Long) :
            AndroidViewModel(application) {

    private val material: ObservableField<Material> = ObservableField()
    private val mObservableMaterial: LiveData<Material> = dataRepository.getMaterial(id)

    fun setMaterial(material: Material) {
        this.material.set(material)
    }

    fun getMaterial() : Material = material.get()

    fun getObservableMaterial(): LiveData<Material> {
        return mObservableMaterial
    }

    class Factory(private val mApplication: Application, private val mMaterialId: Long) :
            ViewModelProvider.NewInstanceFactory() {

        private val mRepository: DataRepository

        init {
            mRepository = (mApplication as App).getRepository()
        }

        override fun <T : ViewModel> create(modelClass: Class<T>): T {

            return MaterialViewModel(mApplication, mRepository, mMaterialId) as T
        }
    }

}