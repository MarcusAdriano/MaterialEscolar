package com.marcus.materialescolar.view.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.databinding.DataBindingUtil
import android.support.v4.app.Fragment
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import com.marcus.materialescolar.R
import com.marcus.materialescolar.databinding.MaterialFragmentBinding
import com.marcus.materialescolar.model.Material
import com.marcus.materialescolar.view.viewmodel.MaterialViewModel
import kotlinx.android.synthetic.main.material_fragment.*

/**
 * Created by Marcus on 02-Feb-18.
 *
 */
class MaterialFragment : Fragment() {

    private lateinit var mBinding: MaterialFragmentBinding
    private lateinit var mViewModel: MaterialViewModel
    private lateinit var mBtnNext : Button
    private lateinit var mBtnPrev : Button
    private var mIndex: Long = 0

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate this data binding layout
        mBinding = DataBindingUtil.inflate(inflater, R.layout.material_fragment, container, false)
        subscribeUi()

        mBtnNext = mBinding.root.findViewById(R.id.btnNext)
        mBtnPrev = mBinding.root.findViewById(R.id.btnPrev)

        with(mBtnNext) {
            setOnClickListener({ _ ->
                mViewModel.setId(mIndex++)
            })
        }

        with(mBtnPrev) {
            setOnClickListener({ _ ->
                mViewModel.setId(mIndex--)
            })
        }
        return mBinding.root
    }

    private fun subscribeUi() {
        mViewModel = ViewModelProviders.of(this).get(MaterialViewModel::class.java)
        mViewModel.material().observe(this, Observer<Material> {material ->
            mBinding.material = material
        })
        mIndex = arguments.getLong(KEY_ID)
        mViewModel.setId(mIndex)
    }

    companion object {
        @JvmStatic val KEY_ID = "materialId"
    }
}