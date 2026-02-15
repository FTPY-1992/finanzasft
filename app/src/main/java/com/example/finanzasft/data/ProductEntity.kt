package com.example.finanzasft.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class ProductEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val brand: String?,
    val description: String?,
    val price: Double,
    val quantityBought: Int,
    val quantityConsumed: Int,
    val photoPath: String? = null,
    val category: Category? = null
)