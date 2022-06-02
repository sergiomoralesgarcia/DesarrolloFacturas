package com.example.desarrollofacturas

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.desarrollofacturas.FacturaDetail.Companion.start

data class Facturas(val fecha: String, val descEstado: String, val importeOrdenacion: String)

class FacturasAdapter(facturas: ArrayList<Facturas>, context: Context) :
    RecyclerView.Adapter<FacturasAdapter.FacturasViewHolder>() {

    val facturas = facturas
    val context = context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FacturasViewHolder {
        return FacturasViewHolder(
            LayoutInflater.from(this.context).inflate(R.layout.factura_item, parent, false)
        )
    }

    override fun getItemCount(): Int = this.facturas.size

    override fun onBindViewHolder(holder: FacturasViewHolder, position: Int) {
        holder.bind(this.facturas.get(position), context)
    }


    // Asignación valores factura_item
    class FacturasViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var container: ConstraintLayout? = null
        var fecha: TextView? = null
        var descEstado: TextView? = null
        var importeOrdenacion: TextView? = null

        init {
            container = itemView.findViewById(R.id.itemContainer)
            fecha = itemView.findViewById(R.id.fecha)
            descEstado = itemView.findViewById(R.id.descEstado)
            importeOrdenacion = itemView.findViewById(R.id.importeOrdenacion)
        }

        fun bind(facturas: Facturas, context: Context) {
            fecha?.text = facturas.fecha
            descEstado?.text = facturas.descEstado
            importeOrdenacion?.text = facturas.importeOrdenacion
            container?.setOnClickListener {
                start(context, facturas.fecha, facturas.descEstado, facturas.importeOrdenacion)
            }
        }
    }
}