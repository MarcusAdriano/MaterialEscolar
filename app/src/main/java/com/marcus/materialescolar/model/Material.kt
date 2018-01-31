package com.marcus.materialescolar.model

import android.arch.persistence.room.*
import android.support.annotation.IntegerRes

/**
 * Created by Marcus on 30-Jan-18.
 *
 */
@Entity(tableName = "materiais")
@ForeignKey(entity = Marca::class,
            childColumns = arrayOf("marcadId"),
            parentColumns = arrayOf("marcaId"),
            onDelete = ForeignKey.NO_ACTION,
            onUpdate = ForeignKey.NO_ACTION)
open class Material {

    /**
     * Id (number incremented
     */
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    /**
     * Name/description
     */
    @ColumnInfo(name = "name")
    lateinit var name: String

    /**
     * Number of this material
     */
    @ColumnInfo(name = "quantity")
    var quantity: Int = 0

    /**
     * unity price
     */
    @ColumnInfo(name = "price")
    var price: Double = 0.0

    @ColumnInfo(name = "marcaId")
    var marcaId: Int = 0

    @Ignore
    constructor()

    @Ignore
    constructor(name: String, quantity: Int, price: Double) {
        this.name = name
        this.quantity = quantity
        this.price = price
    }

    @Ignore
    constructor(id: Int, name: String, quantity: Int, price: Double, marcaId: Int) {
        this.id = id
        this.name = name
        this.quantity = quantity
        this.price = price
        this.marcaId = marcaId
    }

    constructor(name: String, quantity: Int, price: Double, marcaId: Int) {
        this.name = name
        this.quantity = quantity
        this.price = price
        this.marcaId = marcaId
    }
}