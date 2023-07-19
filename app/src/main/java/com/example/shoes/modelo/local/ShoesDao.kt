package com.example.shoes.modelo.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.shoes.modelo.local.entities.DetailsShoesEntity
import com.example.shoes.modelo.local.entities.ShoesEntity

@Dao
interface ShoesDao {

    // insertar lista

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllShoes(listShoes: List<ShoesEntity>)


    @Query("SELECT * FROM TABLE_SHOES  ORDER BY id ASC")
    fun getAllShoes(): LiveData<List<ShoesEntity>>


    // INSERTA UN ELEMENTO

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShoesDetail(shoes: DetailsShoesEntity)


    // da error por momentanemante no se ocupa
    @Query("SELECT * FROM TABLE_SHOES_DETAILS  WHERE id=:id")
    fun  getShoesDetailByID(id:String): LiveData<DetailsShoesEntity?>
}