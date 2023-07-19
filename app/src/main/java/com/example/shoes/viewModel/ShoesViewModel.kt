package com.example.shoes.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.shoes.modelo.ShoesRepository
import com.example.shoes.modelo.local.database.ShoesRoomDatabase
import com.example.shoes.modelo.local.entities.DetailsShoesEntity
import com.example.shoes.modelo.local.entities.ShoesEntity
import kotlinx.coroutines.launch

class ShoesViewModel (application: Application): AndroidViewModel(application){

    private val repository : ShoesRepository // tomamoes el repositorio que es el fin del modelo

    // entidad
    private val shoesDetailLiveData = MutableLiveData<DetailsShoesEntity>()

    private  var idSelected: String ="-1"

    init{
        val bd= ShoesRoomDatabase.getDataBase(application) //Instanciar la base de datos  (para acceder a ella)
        val  shoesDao = bd.getShoesDao()  //Despues de instanciar la bade de datos, entramos y sacamos la funcion getPhoneDao

        repository = ShoesRepository(shoesDao) // Recuerda que el Repository Recibe un PhoneDao, pero no la clase sino la variable que hemos creado y que tb viene de ña bd

        // lama el método del respositorio
        viewModelScope.launch {

            repository.fechShoes()// esta es una función que viene del PhoneRepository, antes hemos instanciado el Repositorio como repository
        }
    }

    // listado de elementos

    fun getShoesList(): LiveData<List<ShoesEntity>> = repository.phoneListLiveData //Futuro:: esto se usará en el 1ER FRAGMENTO
    // el Live DATA está tomando una lista de entidad PhoneEntity  y lo iguala a una variable del repositorio


    // obtener el detalle envuelto en liveData

    fun getShoesDetail(): LiveData<DetailsShoesEntity> = shoesDetailLiveData // el live data toma una entidad de DETALLE PHONE sin lista y los iguala a una variable que existe aqui en el VIEWMODEL


    // funcion para seleccionar elemento

    fun getShoesDetailByIDFromInternet(id:String) = viewModelScope.launch { //Futuro:: esto se usara en el 2DO FRAGMENTO

        val shoeDetail = repository.fetchShoesDetail(id)  // IGUALAMOS la variable con una funcion que viene del Repositorio que recibe un id tipo String ENTRE SUS VARIABLES
        shoeDetail?.let {

            shoesDetailLiveData.postValue(it)
        }
    }




}