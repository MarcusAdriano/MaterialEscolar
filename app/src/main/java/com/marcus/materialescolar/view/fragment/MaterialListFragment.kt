package com.marcus.materialescolar.view.fragment

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.marcus.materialescolar.R
import com.marcus.materialescolar.databinding.ListFragmentBinding
import com.marcus.materialescolar.view.activity.MainActivity
import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.Observer
import com.marcus.materialescolar.model.Material
import com.marcus.materialescolar.view.MaterialClickCallback
import com.marcus.materialescolar.view.adapter.MaterialAdapter
import android.arch.lifecycle.ViewModelProviders
import com.marcus.materialescolar.view.viewmodel.MaterialListViewModel


/**
 * Created by Marcus on 03-Feb-18.
 *
 */
class MaterialListFragment : Fragment() {

    private lateinit var mBinding: ListFragmentBinding
    private lateinit var mMaterialAdapter: MaterialAdapter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.list_fragment, container, false);

        mMaterialAdapter = MaterialAdapter(mMaterialCallback);
        mBinding.productsList.setAdapter(mMaterialAdapter);

        return mBinding.getRoot();
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val viewModel = ViewModelProviders.of(this).get(MaterialListViewModel::class.java!!)

        subscribeUi(viewModel)
    }

    private fun subscribeUi(viewModel: MaterialListViewModel) {
        // Update the list when the data changes
        viewModel.materiais.observe(this, object : Observer<List<Material>> {

            override fun onChanged(myMateriais: List<Material>?) {
                if (myMateriais != null) {
                    mBinding.isLoading = false
                    mMaterialAdapter.materialList = myMateriais
                } else {
                    mBinding.isLoading = true
                }
                // espresso does not know how to wait for data binding's loop so we execute changes
                // sync.
                mBinding.executePendingBindings()
            }
        })
    }

    private val mMaterialCallback = object : MaterialClickCallback {
        override fun onClick(material: Material) {
            if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
                (activity as MainActivity).show(material)
            }
        }
    }

    companion object {
        val TAG = "MaterialListFragment"
    }
}