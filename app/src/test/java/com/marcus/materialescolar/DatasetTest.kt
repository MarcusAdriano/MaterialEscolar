package com.marcus.materialescolar

import com.marcus.materialescolar.model.Material
import org.junit.Before
import org.junit.Test

/**
 * Created by Marcus on 31-Jan-18.
 */
class DatasetTest {

    lateinit var dataset : List<Material>
    val refLista = ListaEscolar

    @Before
    fun setup() {
        dataset = refLista.getAll()
    }

    @Test
    fun addTest() {
        refLista.addMaterial(Material("", 0, 20.0))
        refLista.addMaterial(Material("", 0, 20.0))
        assert(dataset.size == 2)
    }

    @Test
    fun deleteTest() {
        while (refLista.size() > 0) {
            refLista.deleteMaterial(0)
        }

        assert(dataset.size == 0)
    }
}