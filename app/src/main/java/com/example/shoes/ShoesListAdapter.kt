package com.example.shoes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shoes.databinding.ArticleItemBinding
import com.example.shoes.modelo.local.entities.ShoesEntity

class ShoesListAdapter : RecyclerView.Adapter<ShoesListAdapter.PhoneVH>() {

    //RECUERDA el adapter esta para conectar el recyclerView con (Article_item.xml)

    private var listShoes = listOf<ShoesEntity>()
    private val SelectedShoes = MutableLiveData<ShoesEntity>()



    fun update(list:List<ShoesEntity>){
        listShoes= list
        notifyDataSetChanged()
    }


    // FUNCION PARA SELECCIONAR

    fun selectedShoe():
            LiveData<ShoesEntity> = SelectedShoes


    inner class  PhoneVH(private val mBinding: ArticleItemBinding): // el binding viene del (article_item.xml) del detalle del recyclerView
        RecyclerView.ViewHolder(mBinding.root), View.OnClickListener{

        fun bind(shoesEntity: ShoesEntity){
            Glide.with(mBinding.listImage).load(shoesEntity.shoesImage).centerCrop().into(mBinding.listImage)

            mBinding.name.text = shoesEntity.shoesName // NO TE olvides del .TEXT
            mBinding.marca.text = shoesEntity.shoesMarca
            mBinding.idMostrar.text = shoesEntity.id // recuerda agregar.TEXT al mBinding

            itemView.setOnClickListener(this)

        }
        override  fun onClick(v: View){

            SelectedShoes.value= listShoes[adapterPosition]
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhoneVH {
        return PhoneVH(ArticleItemBinding.inflate(LayoutInflater.from(parent.context)))
    }


    override fun onBindViewHolder(holder: PhoneVH, position: Int) {
        val course = listShoes[position]
        holder.bind(course)
    }


    override fun getItemCount()=
        listShoes.size


}