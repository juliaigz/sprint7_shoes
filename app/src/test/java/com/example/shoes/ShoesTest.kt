package com.example.shoes

import com.example.shoes.modelo.local.entities.DetailsShoesEntity
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class ShoesTest {

    private lateinit var shoes: DetailsShoesEntity
    @Before
    fun setUp() {
        // Configurar el entorno de prueba
        val id = "1"
        val shoesName = "Example Shoe"
        val shoesOrigen = "Nigeria"
        val shoesImage = "example_image.jpg"
        val shoesMarca = "This is an example phone"
        val shoesNumber = "talla 36"
        val shoesPrecio = "12300"
        val shoesEntrega = true

        shoes = DetailsShoesEntity(
            id = id,
            shoesName = shoesName,
            shoesOrigen = shoesOrigen,
            shoesImage = shoesImage,
            shoesMarca = shoesMarca,
            shoesNumber = shoesNumber,
            shoesPrice = shoesPrecio,
            shoesEntrega = shoesEntrega
        )
    }
    @After
    fun tearDown() {
        // Limpiar el entorno de prueba si es necesario
    }
    @Test
    fun testPhoneConstructor() {
        // Assert
        Assert.assertEquals("1", shoes.id)
        Assert.assertEquals("Example Shoe", shoes.shoesName)
        Assert.assertEquals("Nigeria", shoes.shoesOrigen)
        Assert.assertEquals("example_image.jpg", shoes.shoesImage)
        Assert.assertEquals("This is an example shoe", shoes.shoesMarca)
        Assert.assertEquals("talla 36", shoes.shoesNumber)
        Assert.assertEquals("12300", shoes.shoesPrice)
        Assert.assertTrue(shoes.shoesEntrega)
    }

    // Otros tests unitarios para métodos adicionales de la clase Phone


}