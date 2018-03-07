package com.marcus.materialescolar.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.marcus.materialescolar.databinding.MaterialItemBinding
import com.marcus.materialescolar.model.Material
import android.view.LayoutInflater
import android.databinding.DataBindingUtil
import com.marcus.materialescolar.R
import com.marcus.materialescolar.view.MaterialClickCallback

/**
 * Created by Marcus on 31-Jan-18.
 *
 */
class MaterialAdapter(val clickCallback: MaterialClickCallback) :
        RecyclerView.Adapter<MaterialAdapter.MaterialViewHolder>() {

    private var materialList: List<Material>? = null

    fun setMaterialList(list: List<Material>) {
        materialList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MaterialViewHolder {
        val binding : MaterialItemBinding = DataBindingUtil
                .inflate(
                        LayoutInflater.from(parent!!.context), R.layout.material_item,
                        parent, false)

        binding.setCallback(clickCallback)
        return MaterialViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return if (materialList == null) 0 else materialList!!.size
    }

    override fun onBindViewHolder(holder: MaterialViewHolder?, position: Int) {
        holder!!.binding.material = materialList!!.get(position)
        holder.binding.executePendingBindings()
    }

    class MaterialViewHolder(val binding: MaterialItemBinding) :
            RecyclerView.ViewHolder(binding.root)
}