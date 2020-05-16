package com.example.finalandroid.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.finalandroid.Model.Hospital
import com.example.finalandroid.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_lista.*

/**
 * A simple [Fragment] subclass.
 */
class ListaFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lista, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lista_hospitales.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = HospitalRecyclerAdapter(mutableListOf<Hospital>())
        }
        getProducts()
    }


    public fun getProducts(){
        val ref = FirebaseDatabase.getInstance().getReference("/hospitales")

        ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(p0: DataSnapshot) {
                val list = mutableListOf<Hospital>()
                p0.children.forEach {
                    val product = it.getValue(Hospital::class.java)
                    list.add(product!!)

                }
                lista_hospitales.adapter = HospitalRecyclerAdapter(list)
            }


        } )
    }

}
