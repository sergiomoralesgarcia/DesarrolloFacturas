package com.example.desarrollofacturas

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_note_detail.*
import kotlinx.android.synthetic.main.activity_notes_filtros.*
import kotlinx.android.synthetic.main.note_item.*

class Constants {
    companion object {
        const val FACTURE_TITLE_KEY = "FACTURE_TITLE_KEY"
        const val FACTURE_DATE_KEY = "FACTURE_DATE_KEY"
        const val FACTURE_CONTENT_KEY = "FACTURE_CONTENT_KEY"
    }
}

class NoteDetail : AppCompatActivity() {

    var title: String? = null
    var date: String? = null
    var content: String? = null

    companion object {
        @JvmStatic
        fun start(context: Context, title: String, creationDate: String, content: String) {
            val starter = Intent(context, NoteDetail::class.java)
                .putExtra(Constants.FACTURE_TITLE_KEY, title)
                .putExtra(Constants.FACTURE_DATE_KEY, creationDate)
                .putExtra(Constants.FACTURE_CONTENT_KEY, content)
            context.startActivity(starter)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_detail)

        popup_window_button.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
        }

        if (intent != null) {
            title = intent.getStringExtra(Constants.FACTURE_TITLE_KEY)
            date = intent.getStringExtra(Constants.FACTURE_DATE_KEY)
            content = intent.getStringExtra(Constants.FACTURE_CONTENT_KEY)



        }
    }

}