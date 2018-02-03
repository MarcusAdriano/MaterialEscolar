package com.marcus.materialescolar

import com.marcus.materialescolar.model.Material
import java.util.LinkedList

/**
 * Created by Marcus on 30-Jan-18.
 * ============ SINGLETON ===============
 *
 */
object ListaEscolar {

    private val materiais : LinkedList<Material> = LinkedList()
    var totalPrice : Double = 0.0
        private set

    fun addMaterial(value: Material) {
        materiais.add(value)
        totalPrice += value.unityPrice * value.quantity
    }

    fun getAll() : List<Material> = materiais
    fun get(index: Int) : Material = materiais.get(index)

    fun deleteMaterial(index: Int) : Material {
        val material = materiais.removeAt(index)
        return deleteMaterial(material)
    }

    fun deleteMaterial(material: Material) : Material {
        materiais.remove(material)
        totalPrice -= material.unityPrice * material.quantity
        return material
    }

    fun clear() {
        materiais.clear()
        totalPrice = 0.0
    }

    fun size() : Int = materiais.size

}