package com.marcus.materialescolar.view.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.marcus.materialescolar.R
import com.marcus.materialescolar.model.Material
import com.marcus.materialescolar.view.fragment.MaterialFragment
import com.marcus.materialescolar.view.fragment.MaterialListFragment


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

    fun show(material: Material) {
        val materialFragment = MaterialFragment.forMaterial(material.id)

        supportFragmentManager
                .beginTransaction()
                .addToBackStack("material")
                .replace(R.id.fragment_container,
                        materialFragment, null).commit()
    }
}
