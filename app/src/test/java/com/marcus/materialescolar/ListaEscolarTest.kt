package com.marcus.materialescolar

import com.marcus.materialescolar.model.Material
import org.junit.Before
import org.junit.Test

/**
 * Created by Marcus on 30-Jan-18.
 *
 */
class ListaEscolarTest {

    val lista = ListaEscolar
    var total: Double = 0.0

    @Before
    fun addMateriais() {
        lista.addMaterial(Material("", 2, 20.0))
        lista.addMaterial(Material("", 3, 10.0))
        total = 2 * 20 + 3 * 10.0
    }

    @Test
    fun totalPriceTest() {
        assert(lista.totalPrice == total)
        lista.deleteMaterial(0)
        assert(lista.totalPrice == 30.0)
    }

    @Test
    fun clearTest() {
        lista.clear()
        assert(lista.totalPrice == 0.0)
        assert(lista.size() == 0)
    }
}