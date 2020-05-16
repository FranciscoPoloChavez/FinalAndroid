package com.example.finalandroid.fragments

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.finalandroid.Model.Hospital
import com.example.finalandroid.R

class HospitalRecyclerAdapter( private val list: List<Hospital>)
    : RecyclerView.Adapter<ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ItemViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item: Hospital = list[position]
        holder.bind(item)

    }

    override fun getItemCount(): Int = list.size

}

class ItemViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.renglon_hospital, parent, false)) {
    private var nombreTextView: TextView? = null
    private var calleTextView: TextView? = null
    private var numeroTextView: TextView? = null


    init {
        nombreTextView = itemView.findViewById(R.id.nombreHospital)
        calleTextView = itemView.findViewById(R.id.direccionHospital)
        numeroTextView = itemView.findViewById(R.id.telefonoHospital)
    }

    fun bind(item: Hospital) {
        Log.i("Error despensa",item.nombre )
        nombreTextView?.text = item.nombre
        calleTextView?.text = item.ubicacion
        numeroTextView?.text = item.telefono

    }

}