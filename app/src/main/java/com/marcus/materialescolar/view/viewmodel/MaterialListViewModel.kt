package com.marcus.materialescolar.view.viewmodel

import android.app.Application
import android.arch.lifecycle.*
import com.marcus.materialescolar.model.Material
import com.marcus.materialescolar.App
import com.marcus.materialescolar.DataRepository

/**
 * Created by Marcus on 03-Feb-18.
 *
 */
class MaterialListViewModel(application: Application) : AndroidViewModel(application) {

    private var dataRepository: DataRepository = (application as App).getRepository()
    private var mObservableMateriais: LiveData<List<Material>> = dataRepository.getMateriais()

    /**
     * Expose the LiveData Materiais query so the UI can observe it.
     */
    val materiais: LiveData<List<Material>>
        get() = mObservableMateriais
}
