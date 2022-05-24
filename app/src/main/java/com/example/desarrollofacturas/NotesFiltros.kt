package com.example.desarrollofacturas

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.slider.RangeSlider
import kotlinx.android.synthetic.main.activity_notes_filtros.*
import java.io.RandomAccessFile
import java.util.*

class NotesFiltros : AppCompatActivity(), SeekBar.OnSeekBarChangeListener {
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

        // Cambio progresivo de la cantidad del SeekBar
        var progressView: TextView? = null
        var seekBar: SeekBar? = null

        progressView = this.cantidad2
        seekBar = this.seekBar
        seekBar!!.setOnSeekBarChangeListener(this)

        // Botón de aplicar los filtros
        botonAplicar.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        // Botón de eliminar los filtros
        botonEliminar.setOnClickListener {
            startActivity(Intent(this, NotesFiltros::class.java))
        }
    }

    // importaciones del SeekBar
    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        cantidad2!!.text = progress.toString()
    }

    override fun onStartTrackingTouch(p0: SeekBar?) {}

    override fun onStopTrackingTouch(p0: SeekBar?) {}
}