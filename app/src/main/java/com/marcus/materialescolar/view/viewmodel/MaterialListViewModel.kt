package com.marcus.materialescolar.view.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MediatorLiveData
import com.marcus.materialescolar.model.Material
import android.arch.lifecycle.LiveData
import com.marcus.materialescolar.App

/**
 * Created by Marcus on 03-Feb-18.
 *
 */
class MaterialListViewModel(application: Application) : AndroidViewModel(application) {

    // MediatorLiveData can observe other LiveData objects and react on their emissions.
    private val mObservableProducts: MediatorLiveData<List<Material>> = MediatorLiveData<List<Material>>()

    /**
     * Expose the LiveData Materiais query so the UI can observe it.
     */
    val materiais: LiveData<List<Material>>
        get() = mObservableProducts

    init {

        // set by default null, until we get data from the database.
        mObservableProducts.value = null

        val mats = (application as App).getRepository()
                .getMateriais()

        // observe the changes of the materiais from the database and forward them
        mObservableProducts.addSource<List<Material>>(mats, { mObservableProducts.setValue(it) })
    }
}
