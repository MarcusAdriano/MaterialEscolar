package com.marcus.materialescolar.view

import android.databinding.BindingAdapter
import android.view.View


/**
 * Created by Marcus on 02-Feb-18.
 *
 */
object BindingAdapters {
    @BindingAdapter("visibleGone")
    fun showHide(view: View, show: Boolean) {
        view.setVisibility(if (show) View.VISIBLE else View.GONE)
    }
}