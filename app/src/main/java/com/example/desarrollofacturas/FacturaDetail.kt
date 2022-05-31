package com.example.desarrollofacturas

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.view.animation.DecelerateInterpolator
import androidx.core.graphics.ColorUtils
import kotlinx.android.synthetic.main.activity_factura_detail.*
import kotlinx.android.synthetic.main.activity_facturas_filtros.*
import kotlinx.android.synthetic.main.factura_item.*

// Constantes que me permiten transladar información
class Constants {
    companion object {
        const val FACTURE_TITLE_KEY = "FACTURE_TITLE_KEY"
        const val FACTURE_DATE_KEY = "FACTURE_DATE_KEY"
        const val FACTURE_CONTENT_KEY = "FACTURE_CONTENT_KEY"
    }
}

class FacturaDetail : AppCompatActivity() {

    var title: String? = null
    var content: String? = null
    var close: String? = null

    // Iniciar la vista
    companion object {
        @JvmStatic
        fun start(context: Context, title: String, content: String, close: String) {
            val starter = Intent(context, FacturaDetail::class.java)
                .putExtra(Constants.FACTURE_TITLE_KEY, title)
                .putExtra(Constants.FACTURE_DATE_KEY, content)
                .putExtra(Constants.FACTURE_CONTENT_KEY, close)
            context.startActivity(starter)
        }
    }

    //variables del popup
    private var popupTitle = ""
    private var popupText = ""
    private var popupButton = ""
    private var darkStatusBar = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_factura_detail)
        overridePendingTransition(5, 5)

        // Obtener los datos
        val bundle = intent.extras
        popupTitle = bundle?.getString("popuptitle", "Información") ?: ""
        popupText = bundle?.getString("popuptext", "Esta funcionalidad no está disponible") ?: ""
        popupButton = bundle?.getString("popupbtn", "Cerrar") ?: ""
        darkStatusBar = bundle?.getBoolean("darkstatusbar", false) ?: false

        // Establecer los datos
        popup_window_title.text = popupTitle
        popup_window_text.text = popupText
        popup_window_button.text = popupButton

        // Animación de fundido para el fondo de la ventana emergente
        val alpha = 105
        val alphaColor = ColorUtils.setAlphaComponent(Color.parseColor("#004CAF50"), alpha)
        val colorAnimation = ValueAnimator.ofObject(ArgbEvaluator(), Color.TRANSPARENT, alphaColor)
        colorAnimation.duration = 600 // milisegundos
        colorAnimation.addUpdateListener { animator ->
            popup_window_background.setBackgroundColor(animator.animatedValue as Int)
        }
        colorAnimation.start()

        // Animación de fundido para la ventana emergente
        popup_window_view_with_border.alpha = 0f
        popup_window_view_with_border.animate().alpha(1f).setDuration(1000).setInterpolator(
            DecelerateInterpolator()
        ).start()

        // Cierre de la ventana emergente cuando presione el botón
        popup_window_button.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setWindowFlag(activity: Activity, on: Boolean) {
        val win = activity.window
        val winParams = win.attributes
        if (on) {
            winParams.flags = winParams.flags or WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
        } else {
            winParams.flags =
                winParams.flags and WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS.inv()
        }
        win.attributes = winParams
    }


    override fun onBackPressed() {
        // Animación de fundido para el fondo de la ventana emergente cuando presiona el botón Cerrar
        val alpha = 100
        val alphaColor = ColorUtils.setAlphaComponent(Color.parseColor("#FF488106"), alpha)
        val colorAnimation = ValueAnimator.ofObject(ArgbEvaluator(), alphaColor, Color.TRANSPARENT)
        colorAnimation.duration = 500 // milisegundos
        colorAnimation.addUpdateListener { animator ->
            popup_window_background.setBackgroundColor(
                animator.animatedValue as Int
            )
        }

        // Animación de fundido para la ventana emergente cuando presiona el botón Atrás
        popup_window_view_with_border.animate().alpha(0f).setDuration(400).setInterpolator(
            DecelerateInterpolator()
        ).start()

        // Después de que termine la animación, cerramos la Actividad
        colorAnimation.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                finish()
                overridePendingTransition(0, 0)
            }
        })
        colorAnimation.start()


        // Botón de cierre de la pestaña de información
        popup_window_button.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        // si existe el intent, asigna los valores a las variables
        if (intent != null) {
            title = intent.getStringExtra(Constants.FACTURE_TITLE_KEY)
            content = intent.getStringExtra(Constants.FACTURE_DATE_KEY)
            close = intent.getStringExtra(Constants.FACTURE_CONTENT_KEY)
        }
    }

}