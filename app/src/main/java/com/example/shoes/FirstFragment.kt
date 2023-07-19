package com.example.shoes

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shoes.databinding.FragmentFirstBinding
import com.example.shoes.viewModel.ShoesViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private lateinit var mBinding: FragmentFirstBinding
    private val mViewModel : ShoesViewModel by activityViewModels()


    // This property is only valid between onCreateView and
    // onDestroyView.
    //private val binding get() = mBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mBinding = FragmentFirstBinding.inflate(inflater, container, false)
        return mBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // DEBEMOS INTANCIAR ADAPTER

        val adapter = ShoesListAdapter()
        mBinding.listaRecyclerView.adapter=adapter //tomamos el id del recyclerView
        //mBinding.listaRecyclerView.layoutManager = GridLayoutManager(this.requireContext(),3) //Esto lo convierte en 3 columnas
        mBinding.listaRecyclerView.layoutManager= LinearLayoutManager(context)

        val recyclerView: RecyclerView = view.findViewById(R.id.listaRecyclerView)
        recyclerView.layoutManager = GridLayoutManager(this.requireContext(),2) //PARA LAS COLUMNAS


        mViewModel.getShoesList().observe(viewLifecycleOwner, Observer { //del viewModel obtenemos el listado de elementos (getPhoneList())
            it?.let {
                adapter.update(it)
            }
        })

        // MÉTODO PARA SELECCIONAR

        adapter.selectedShoe().observe(viewLifecycleOwner,Observer{ //selectedPhone viene del adapter
            it?.let {
                //validar si capta la seleccion
                Log.d("seleccion", it.id.toString())
            }
            val bundle = Bundle().apply {
                putString("courseId",it.id)
            }
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment,bundle)
        })


    }

    override fun onDestroyView() {
        super.onDestroyView()
        //mBinding = null
    }
}