package com.marcus.materialescolar.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.marcus.materialescolar.databinding.MaterialItemBinding
import com.marcus.materialescolar.model.Material
import android.view.LayoutInflater
import android.databinding.DataBindingUtil
import com.marcus.materialescolar.R
import com.marcus.materialescolar.view.MaterialClickCallback
import android.support.v7.util.DiffUtil

/**
 * Created by Marcus on 31-Jan-18.
 *
 */
class MaterialAdapter(val clickCallback: MaterialClickCallback) :
        RecyclerView.Adapter<MaterialAdapter.MaterialViewHolder>() {

    var materialList: List<Material>? = null
        get
        set(value) {
            if (this.materialList == null) {
                field = value
                notifyItemRangeInserted(0, this.materialList!!.size)
            } else {
                val result = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
                    override fun getOldListSize(): Int {
                        return materialList!!.size
                    }

                    override fun getNewListSize(): Int {
                        return materialList!!.size
                    }

                    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                        return materialList!!.get(oldItemPosition).id == materialList!!.get(newItemPosition).id
                    }

                    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                        val newMaterial = value!!.get(newItemPosition)
                        val oldMaterial = materialList!!.get(oldItemPosition)
                        return (newMaterial.id == oldMaterial.id
                                && newMaterial.name == oldMaterial.name
                                && newMaterial.marca == oldMaterial.marca
                                && newMaterial.quantity == oldMaterial.quantity
                                && newMaterial.unityPrice == oldMaterial.unityPrice)
                    }
                })
                field = value
                result.dispatchUpdatesTo(this)
            }
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