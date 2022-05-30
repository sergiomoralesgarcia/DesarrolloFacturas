package com.example.desarrollofacturas

import android.content.Context
import android.text.Layout
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.desarrollofacturas.FacturaDetail.Companion.start

data class Factura(val title: String, val date: String, val content: String)

class FacturasAdapter(facturas: ArrayList<Factura>, context: Context) :
    RecyclerView.Adapter<FacturasAdapter.NotesViewHolder>() {

    val facturas = facturas
    val context = context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        return NotesViewHolder(
            LayoutInflater.from(this.context).inflate(R.layout.note_item, parent, false)
        )
    }

    override fun getItemCount(): Int = this.facturas.size

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.bind(this.facturas.get(position), context)
    }


    // Asignación valores note_item
    class NotesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var container: ConstraintLayout? = null
        var title: TextView? = null
        var date: TextView? = null
        var content: TextView? = null

        init {
            container = itemView.findViewById(R.id.itemContainer)
            title = itemView.findViewById(R.id.fecha)
            date = itemView.findViewById(R.id.descEstado)
            content = itemView.findViewById(R.id.importeOrdenacion)
        }

        fun bind(factura: Factura, context: Context) {
            title?.text = factura.title
            date?.text = factura.date
            content?.text = factura.content
            container?.setOnClickListener {
                start(context, factura.title, factura.date, factura.content)
            }
        }
    }
}