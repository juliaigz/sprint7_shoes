package com.example.shoes.modelo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.shoes.modelo.local.ShoesDao
import com.example.shoes.modelo.local.entities.DetailsShoesEntity
import com.example.shoes.modelo.remote.apiRetrofit.RetrofitClient

class ShoesRepository (private val shoesDao: ShoesDao){

    //retrofit cliente
    private val networkService = RetrofitClient.retrofitInstance()

    // dao

    val phoneListLiveData = shoesDao.getAllShoes()

    //Variable local
    val phoneDetailLiveData = MutableLiveData<DetailsShoesEntity>()



    // insertar el listado de phones
    suspend fun  fechShoes(){ // suspend fun es de courrutines

        val service = kotlin.runCatching { networkService.fetchShoesList()}  // ctrl click te busca donde esta esa función en el código : suspend fun fetchPhoneList(): Response<List<PhoneApiClass>>

        service.onSuccess {

            when(it.code()){
                in 200..299-> it.body()?.let {
                    // insertando la lista de cursos
                    shoesDao.insertAllShoes(fromInternetToShoesEntity(it)) // viene del Dao ( insertall) y viene del mapper (fromInternetToPhoneEntity)
                    //insertAllPhones(listPhones: List<PhoneEntity>) esto es del Dao
                }

                else -> Log.d("Repo", "${it.code()}-${it.errorBody()}")
            }
            service.onFailure {
                Log.e("Error","${it.message}")
            }

        }

    }


    // insertar uan tarea

    suspend fun fetchShoesDetail(id: String): DetailsShoesEntity? {
        val service = kotlin.runCatching { networkService.fechShoesDetail(id)} //.fechCourseDetail(id)
        return service.getOrNull()?.body()?.let { phoneDetail ->
            // guardp los datos que viene del mapper y luego se los paso a dao directo
            val phoneDetailEntity = fromInternetToShoesDetailEntity(phoneDetail) // viene del mapper ( fromInternet
            //inserto los detalles de los curso DEL REPOSITORIO
            shoesDao.insertShoesDetail(phoneDetailEntity) //la función viene del PhoneDao  :  suspend fun insertPhoneDetail(course: DetailsShoesEntity)
            phoneDetailEntity
        }
    }




}