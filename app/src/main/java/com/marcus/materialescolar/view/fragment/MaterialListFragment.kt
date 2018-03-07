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
import android.support.annotation.Nullable
import android.util.Log
import com.marcus.materialescolar.view.viewmodel.MaterialListViewModel
import kotlinx.android.synthetic.main.list_fragment.*


/**
 * Created by Marcus on 03-Feb-18.
 *
 */
class MaterialListFragment : Fragment() {

    private lateinit var mBinding: ListFragmentBinding
    private lateinit var mMaterialAdapter: MaterialAdapter
    private lateinit var mViewModel : MaterialListViewModel

    @Nullable
    override fun onCreateView(inflater: LayoutInflater?, @Nullable container: ViewGroup?,
                              @Nullable savedInstanceState: Bundle?): View? {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.list_fragment, container, false)

        mMaterialAdapter = MaterialAdapter(mMaterialCallback)
        mBinding.materialList.adapter = mMaterialAdapter
        return mBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewModel = ViewModelProviders.of(this).get(MaterialListViewModel::class.java)

        subscribeUi()
        fab_add.setOnClickListener({ (activity as MainActivity).addMaterial() })
    }

    private fun subscribeUi() {
        // Update the list when the data changes
        mViewModel.materiais.observe(this, Observer<List<Material>> { myMateriais ->
            Log.d(TAG, "Update material list")
            if (myMateriais != null) {
                mBinding.isLoading = false
                mMaterialAdapter.setMaterialList(myMateriais)
            } else {
                mBinding.isLoading = true
            }
            // espresso does not know how to wait for data binding's loop so we execute changes
            // sync.
            mBinding.executePendingBindings()
        })
    }

    private val mMaterialCallback = object : MaterialClickCallback {
        override fun onClick(material: Material) {
            if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
                (activity as MainActivity).showMaterial(material.id)
            }
        }
    }

    companion object {
        const val TAG = "MaterialListFragment"
    }
}