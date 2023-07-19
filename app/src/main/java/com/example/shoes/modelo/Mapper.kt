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
            shoesName = it.name,
            shoesOrigen = it.origen,
            shoesImage = it.image,
            shoesMarca = it.marca,
            shoesNumber = it.number

        )
    }
}


fun fromInternetToShoesDetailEntity(shoes: DetailsShoesApiClass): DetailsShoesEntity{ // se usa luego en el Repository

    return DetailsShoesEntity(
        id=shoes.id,
        shoesName = shoes.name,
        shoesOrigen = shoes.origen,
        shoesImage = shoes.image,
        shoesMarca = shoes.marca,
        shoesNumber = shoes.number,
        shoesPrice = shoes.price,
        shoesEntrega = shoes.Entrega

    )

}