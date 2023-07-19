package com.example.shoes.modelo.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TABLE_SHOES")
data class ShoesEntity (
    @PrimaryKey//(autoGenerate = true)
    val id: String,
    val shoesName: String,
    val shoesOrigen: String,
    val shoesImage: String,
    val shoesMarca: String,
    val shoesNumber: String
        )