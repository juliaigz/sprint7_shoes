package com.example.shoes.modelo.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.shoes.modelo.local.ShoesDao
import com.example.shoes.modelo.local.entities.DetailsShoesEntity
import com.example.shoes.modelo.local.entities.ShoesEntity

@Database(entities = [ShoesEntity::class, DetailsShoesEntity::class], version = 1,
    exportSchema = false)

abstract class ShoesRoomDatabase : RoomDatabase() {

    // representacion del dao
    abstract fun getShoesDao() : ShoesDao  // esto luego se va a usar en el VIEW MODEL

    companion object{

        @Volatile
        private var
                INSTANCE : ShoesRoomDatabase? = null
        fun getDataBase(context: Context) : ShoesRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ShoesRoomDatabase::class.java, "shoes_database")
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }

}