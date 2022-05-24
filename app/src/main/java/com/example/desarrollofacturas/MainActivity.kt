package com.example.desarrollofacturas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_notes_filtros.*
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    var facturas: ArrayList<Factura> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        botonFactura.setOnClickListener{
            startActivity(Intent(this, NotesFiltros::class.java))
        }

        initData()
        initViews()
    }

    private fun initViews() {
        facturasList.setHasFixedSize(true)
        facturasList.adapter = NotesAdapter(this.facturas, this)

    }

    private fun initData() {
        for (i in 1..20) {
            facturas.add(Factura("$i Oct 2021", "Pendiente de pago", "2$i,52€"))
        }
    }

   /* fun botonFactura(v: View?){
        Toast.makeText(this, "este es el noton de facturas", Toast.LENGTH_SHORT).show()
    } */
}