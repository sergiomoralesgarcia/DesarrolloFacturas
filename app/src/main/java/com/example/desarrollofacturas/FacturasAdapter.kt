package com.example.desarrollofacturas

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.desarrollofacturas.FacturaDetail.Companion.start

data class Factura(val date: String, val title: String, val content: String)

class FacturasAdapter(facturas: ArrayList<Factura>, context: Context) :
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
        var date: TextView? = null
        var title: TextView? = null
        var content: TextView? = null

        init {
            container = itemView.findViewById(R.id.itemContainer)
            date = itemView.findViewById(R.id.fecha)
            title = itemView.findViewById(R.id.descEstado)
            content = itemView.findViewById(R.id.importeOrdenacion)
        }

        fun bind(factura: Factura, context: Context) {
            date?.text = factura.date
            title?.text = factura.title
            content?.text = factura.content
            container?.setOnClickListener {
                start(context, factura.date, factura.title, factura.content)
            }
        }
    }
}