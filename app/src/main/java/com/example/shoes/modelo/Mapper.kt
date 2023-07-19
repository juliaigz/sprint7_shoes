package com.example.shoes.modelo

import com.example.shoes.modelo.local.entities.DetailsShoesEntity
import com.example.shoes.modelo.local.entities.ShoesEntity
import com.example.shoes.modelo.remote.fromInternet.DetailsShoesApiClass
import com.example.shoes.modelo.remote.fromInternet.ShoesApiClass

//el mapper traer la información de la API y lo guarda en la database del telefono

fun fromInternetToShoesEntity(phoneList: List<ShoesApiClass>): List<ShoesEntity>{ // se usa luego en el Repository
// it representa al phoneApi
    return phoneList.map {

        ShoesEntity(
            id= it.id,
            shoesName = it.nombre,
            shoesOrigen = it.origen,
            shoesImage = it.imagenLink,
            shoesMarca = it.marca,
            shoesNumber = it.numero

        )
    }
}


fun fromInternetToShoesDetailEntity(shoes: DetailsShoesApiClass): DetailsShoesEntity{ // se usa luego en el Repository

    return DetailsShoesEntity(
        id=shoes.id,
        shoesName = shoes.nombre,
        shoesOrigen = shoes.origen,
        shoesImage = shoes.imagenLink,
        shoesMarca = shoes.marca,
        shoesNumber = shoes.numero,
        shoesPrice = shoes.precio,
        shoesEntrega = shoes.entrega

    )

}