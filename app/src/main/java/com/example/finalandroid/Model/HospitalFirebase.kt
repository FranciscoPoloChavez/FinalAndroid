package com.example.finalandroid.Model

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class HospitalFirebase {
    private lateinit var database: DatabaseReference
    constructor(){
        database = FirebaseDatabase.getInstance().reference
    }

    fun cargaInfo(){
        val hospitales: List<Hospital> = mutableListOf(
            Hospital("","Instituto Nacional de Enfermedades Respiratorias Ismael Cosío","Dirección: Calz. de Tlalpan 4502, Belisario Domínguez Secc 16, Tlalpan, 14080 Ciudad de México, CDMX","55 5487 1700"),
            Hospital("","Hospital General de Zona 32 (Sur)","Dirección: Calz. del Hueso s/n, Coapa, Sta. Úrsula Coapa, Coyoacán, 04980 Ciudad de México, CDMX","55 5677 7244"),
            Hospital("","Medica Sur","Puente de Piedra 150 Col. Toriello Guerra Tlalpan","55 5424 7200")
        )
        hospitales.forEach{
            val key = database.child("hospitales").push().key
            it.id = key
            database.child("hospitales").child(key!!).setValue(it)
        }
    }
}