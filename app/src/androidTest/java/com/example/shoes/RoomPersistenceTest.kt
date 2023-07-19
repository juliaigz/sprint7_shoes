package com.example.shoes

import androidx.lifecycle.LiveData
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.shoes.modelo.local.ShoesDao
import com.example.shoes.modelo.local.database.ShoesRoomDatabase
import com.example.shoes.modelo.local.entities.DetailsShoesEntity
import com.example.shoes.modelo.local.entities.ShoesEntity
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RoomPersistenceTest {

    private lateinit var shoesDao: ShoesDao
    private lateinit var shoesDatabase: ShoesRoomDatabase

    @Before
    fun setup() {
        val context = InstrumentationRegistry.getInstrumentation().context
        shoesDatabase = Room.inMemoryDatabaseBuilder(context, ShoesRoomDatabase::class.java).build()
        shoesDao = shoesDatabase.getShoesDao()
    }

    @After
    fun teardown() {
        shoesDatabase.close()
    }

    @Test
    fun insertAndRetrievePhones() = runBlocking {
        // Arrange
        val shoe1 = ShoesEntity("1", "Shoe 1", "Nigeria", "shoe1.jpg","Bata","talla 37")
        val shoe2 = ShoesEntity("2", "Shoe 2", "Chile", "shoe2.jpg","Nike","talla 38")
        val shoesList = listOf(shoe1, shoe2)


        // Act



        shoesDao.insertAllShoes(shoesList)

        // Assert
        val retrievedPhones = shoesDao.getAllShoes().blockingObserve()
        Assert.assertEquals(2, retrievedPhones?.size)
        assertEquals(shoe1.id, retrievedPhones?.get(0)?.id)
        assertEquals(shoe2.shoesName, retrievedPhones?.get(1)?.shoesName)
    }

    @Test
    fun insertAndRetrievePhoneDetail() = runBlocking {
        // Arrange
        val phoneId = "1"
        val phoneDetail = DetailsShoesEntity(
            phoneId,
            "Shoe 1",
            "Nigeria",
            "shoe1.jpg",
            "Bata",
            "talla 37",
            "12500",
            true
        )

        // Act
        shoesDao.insertShoesDetail(phoneDetail)

        // Assert
        val retrievedPhoneDetail = shoesDao.getShoesDetailByID(phoneId).blockingObserve()
        Assert.assertEquals(phoneId, retrievedPhoneDetail?.id)
        assertEquals(phoneDetail.shoesName, retrievedPhoneDetail?.shoesName)
        assertEquals(phoneDetail.shoesEntrega, retrievedPhoneDetail?.shoesEntrega)
    }

    // Helper function to observe LiveData during tests
    private fun <T> LiveData<T>.blockingObserve(): T? {
        var result: T? = null
        val latch = java.util.concurrent.CountDownLatch(1)
        val observer = androidx.lifecycle.Observer<T> { t ->
            result = t
            latch.countDown()
        }
        observeForever(observer)
        latch.await()
        return result
    }

}