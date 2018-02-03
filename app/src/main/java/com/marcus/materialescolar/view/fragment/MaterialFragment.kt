package com.marcus.materialescolar.view.fragment

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
//import android.databinding.DataBindingUtil
//import android.databinding.ViewDataBinding
import android.support.v4.app.Fragment
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import com.marcus.materialescolar.R
import com.marcus.materialescolar.databinding.MaterialFragmentBinding
//import com.marcus.materialescolar.R
//import com.marcus.materialescolar.databinding.MaterialFragmentBinding
//import com.marcus.materialescolar.databinding.MaterialItemBinding
import com.marcus.materialescolar.model.Material
import com.marcus.materialescolar.view.viewmodel.MaterialViewModel


/**
 * Created by Marcus on 02-Feb-18.
 *
 */
class MaterialFragment : Fragment() {

    private lateinit var mBinding: MaterialFragmentBinding

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate this data binding layout
        mBinding = DataBindingUtil.inflate(inflater, R.layout.material_fragment, container, false)

        return mBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val factory = MaterialViewModel.Factory(
                activity.application, arguments.getLong(MaterialFragment.KEY_MATERIAL_ID))

        val model = ViewModelProviders.of(this, factory)
                .get(MaterialViewModel::class.java!!)

        mBinding.model = model
        subscribeToModel(model)
    }

    private fun subscribeToModel(model: MaterialViewModel) {

        // Observe material data
        model.getObservableMaterial().observe(
                this, object : Observer<Material> {

            override fun onChanged(t: Material?) {
                model.setProduct(t!!)
            }
        })
    }

    companion object {
        /** Creates product fragment for specific product ID  */
        val KEY_MATERIAL_ID = "id"

        fun forMaterial(materialId: Long): Fragment {
            val fragment = MaterialFragment()
            val args = Bundle()
            args.putLong(KEY_MATERIAL_ID, materialId)
            fragment.setArguments(args)
            return fragment
        }
    }
}