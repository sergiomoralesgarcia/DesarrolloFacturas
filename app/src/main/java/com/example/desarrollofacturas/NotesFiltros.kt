package com.example.desarrollofacturas

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.slider.RangeSlider
import kotlinx.android.synthetic.main.activity_notes_filtros.*
import java.io.RandomAccessFile
import java.util.*

class NotesFiltros : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes_filtros)


        // Botón de cancelar los filtros
        cancelButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        // Calendario
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        buttonDate1.setOnClickListener() {
            val dpd = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { view, mYear, mMonth, mDay ->
                    buttonDate1.setText("" + mDay + "/" + (mMonth + 1) + "/" + mYear)
                },
                year,
                month,
                day
            )
            dpd.show()
            // fecha máxima que puedes seleccionar
            dpd.datePicker.maxDate = c.timeInMillis

        }

        buttonDate2.setOnClickListener() {
            val dpd = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { view, mYear, mMonth, mDay ->
                    buttonDate2.setText("" + mDay + "/" + (mMonth + 1) + "/" + mYear)
                },
                year,
                month,
                day
            )
            dpd.show()
            // fecha máxima que puedes seleccionar
            dpd.datePicker.maxDate = c.timeInMillis

        }

        // Botón de aplicar los filtros
        botonAplicar.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        // Botón de eliminar los filtros
        botonEliminar.setOnClickListener {
            startActivity(Intent(this, NotesFiltros::class.java))
        }

    }
}