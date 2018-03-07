package com.marcus.materialescolar.view.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import com.marcus.materialescolar.App
import com.marcus.materialescolar.DataRepository
import com.marcus.materialescolar.model.Material

/**
 * Created by Marcus on 07-Mar-18.
 *
 */
class MaterialViewModel(application: Application) : AndroidViewModel(application) {

    private val dataRepository: DataRepository = (application as App).getRepository()

    /** when user select a material from Material List **/
    private val idLiveData: MutableLiveData<Long> = MutableLiveData()

    fun setId(newValue: Long) { idLiveData.postValue(newValue) }

    fun material() : LiveData<Material> =
            Transformations.switchMap(idLiveData, { id ->
                dataRepository.getMaterial(id) })

}
