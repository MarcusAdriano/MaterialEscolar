package com.marcus.materialescolar.fragment

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.marcus.materialescolar.R

/**
 * Created by Marcus on 30-Jan-18.
 *
 */
class ListaEscolarFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, parent: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater!!.inflate(R.layout.lista_fragment, parent, false)
    }

}