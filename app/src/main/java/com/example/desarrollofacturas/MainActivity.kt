package com.example.desarrollofacturas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var facturas: ArrayList<Factura> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // botón para entrar en los filtros
        botonFactura.setOnClickListener {
            startActivity(Intent(this, FacturasFiltros::class.java))
        }

        initData()
        initViews()
    }


    private fun initViews() {
        facturasList.setHasFixedSize(true)
        facturasList.adapter = FacturasAdapter(this.facturas, this)

    }

    // Generar facturas
    private fun initData() {
        for (i in 1..10) {
            facturas.add(Factura("$i Oct 2021", "Pendiente de pago", "2$i,52€"))
        }
    }
}