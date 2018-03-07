package com.marcus.materialescolar.view.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.marcus.materialescolar.R
import com.marcus.materialescolar.model.Material
import com.marcus.materialescolar.view.fragment.AddFragment
import com.marcus.materialescolar.view.fragment.MaterialFragment
import com.marcus.materialescolar.view.fragment.MaterialListFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            val fragment = MaterialListFragment()

            supportFragmentManager.beginTransaction()
                    .add(R.id.fragment_container, fragment, MaterialListFragment.TAG).commit()
        }
    }

    fun showMaterial(id: Long) {
        val materialFragment = MaterialFragment()
        val args = Bundle()
        args.putLong(MaterialFragment.KEY_ID, id)
        materialFragment.arguments = args

        supportFragmentManager
                .beginTransaction()
                .addToBackStack("material")
                .replace(R.id.fragment_container,
                        materialFragment, null).commit()
    }

    fun addMaterial() {
        val addFragment = AddFragment()

        supportFragmentManager
                .beginTransaction()
                .addToBackStack("material_add")
                .replace(R.id.fragment_container,
                        addFragment, null).commit()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
            handlePopBackStack()
        } else {
            super.onBackPressed()
        }
    }

    fun handlePopBackStack() {
        if (supportFragmentManager.backStackEntryCount == 0) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(false)
        }
    }
}
