package com.marcus.materialescolar.view

import android.databinding.BindingAdapter
import android.view.View

/**
 * Created by Marcus on 05-Feb-18.
 *
 */
object BindingAdapters {

    @BindingAdapter("visibleGone")
    @JvmStatic fun showHide(view: View, show: Boolean) {
        view.visibility = if (show) View.VISIBLE else View.GONE
    }
}