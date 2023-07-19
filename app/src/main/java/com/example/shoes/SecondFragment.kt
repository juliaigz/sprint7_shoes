package com.example.shoes

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.shoes.databinding.FragmentSecondBinding
import com.example.shoes.viewModel.ShoesViewModel

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private lateinit var mBinding: FragmentSecondBinding
    private val mViewModel : ShoesViewModel by activityViewModels()
    //OJO va a almacenar el id del shoe
    private var shoeId :String? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    //private val binding get() = mBinding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mBinding = FragmentSecondBinding.inflate(inflater, container, false)
        return mBinding.root

    }




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)






        //validar que está llegando la variable
        arguments?.let { bundle ->

            shoeId = bundle.getString("courseId")
            Log.d("seleccion2", shoeId.toString())
        }

        shoeId?.let { id ->
            mViewModel.getShoesDetailByIDFromInternet(id) // esta es la 3ERA FUN del PhoneViewModel
        }

        mViewModel.getShoesDetail().observe(viewLifecycleOwner, Observer {
            Log.d("seleccion3",shoeId.toString())
            var id=it.id
            var name=it.shoesName

            Glide.with(mBinding.ivLogo).load(it.shoesImage).into(mBinding.ivLogo)   //conecta el id de la imagen del 2DO FRAGMENTO con la imagen del Entity Details
            mBinding.textId.text = it.id
            mBinding.textName.text = it.shoesName  //conecta EL 2ND FRAGMENT con el entity de  TABLE_PHONE_DETAILS
            mBinding.textOrigen.text = it.shoesOrigen
            mBinding.textMarca.text = it.shoesMarca
            mBinding.textNumero.text = it.shoesNumber
            mBinding.textPrecio.text = it.shoesPrice
            mBinding.textEntrega.text = it.shoesEntrega.toString()

            //correo electronico

            mBinding.btMail.setOnClickListener{
                val mintent = Intent(Intent.ACTION_SEND)
                mintent.data = Uri.parse("mailto")
                mintent.type="text/plain"

                mintent.putExtra(Intent.EXTRA_EMAIL, arrayOf("Zapato.ventas@unica.cl"))
                mintent.putExtra(
                    Intent.EXTRA_SUBJECT,
                    "Consulta por producto :"+name+ "_id"+id
                )

                mintent.putExtra(
                    Intent.EXTRA_TEXT,"Hola\n" +
                            "Vi el producto"+name+"de código,\n"+id+
                            "y me gustaría que me contactaran a este correo o al siguiente número\n" +
                            " _________\n" +
                            "Quedo atento."
                )
                try {
                    startActivity(mintent)
                }catch (e: Exception){
                    Toast.makeText(context,e.message, Toast.LENGTH_LONG).show()
                }
            }



        })







    }

    override fun onDestroyView() {
        super.onDestroyView()
        //mBinding = null
    }
}