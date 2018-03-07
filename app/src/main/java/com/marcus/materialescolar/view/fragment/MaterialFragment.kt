package com.marcus.materialescolar.view.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.databinding.DataBindingUtil
import android.support.v4.app.Fragment
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import com.marcus.materialescolar.R
import com.marcus.materialescolar.databinding.MaterialFragmentBinding
import com.marcus.materialescolar.model.Material
import com.marcus.materialescolar.view.viewmodel.MaterialViewModel

/**
 * Created by Marcus on 02-Feb-18.
 *
 */
class MaterialFragment : Fragment() {

    private lateinit var mBinding: MaterialFragmentBinding
    lateinit var material: Material

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate this data binding layout
        mBinding = DataBindingUtil.inflate(inflater, R.layout.material_fragment, container, false)
        subscribeUi()
        return mBinding.root
    }

    private fun subscribeUi() {
        val viewModel = ViewModelProviders.of(this).get(MaterialViewModel::class.java)
        viewModel.setId(arguments.getLong(KEY_ID))
        viewModel.material().observe(this, Observer<Material> {material ->
            mBinding.material = material
        })
    }

    companion object {
        @JvmStatic val KEY_ID = "materialId"
    }
}