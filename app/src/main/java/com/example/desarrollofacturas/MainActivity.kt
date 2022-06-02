package com.example.desarrollofacturas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.util.Log
import kotlinx.android.synthetic.main.activity_facturas_filtros.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.create
import com.example.desarrollofacturas.FacturasApi as FacturasApi

class MainActivity : AppCompatActivity() {
    var facturas: ArrayList<Facturas> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // botón para entrar en los filtros
        botonFactura.setOnClickListener {
            startActivity(Intent(this, FacturasFiltros::class.java))
        }

       // initData()
        initViews()

        val facturasApi = RetrofitHelper.getInstance().create(FacturasApi::class.java)
        // launching a new coroutine
        GlobalScope.launch {
            val factura = facturasApi.getFacturas()
            if (factura != null)
            // chequeando los resultados
                Log.d("ayush: ", factura.body().toString())
        }
    }


    private fun initViews() {
        facturasList.setHasFixedSize(true)
        facturasList.adapter = FacturasAdapter(this.facturas, this)

    }

    // Generar facturas
    /* private fun initData() {
        for (i in 1..10) {
            facturas.add(Facturas("$i Oct 2021", "Pendiente de pago", "2$i,52€"))
        }
    } */
}