package pe.edu.cibertec.appgrupo1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Pregunta2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pregunta2)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // --- PEGA TU LÓGICA DE CÁLCULO AQUÍ ---

        // 1. Asegúrate de reemplazar los IDs (et_consumo, btn_calcular, tv_resultado)
        // con los nombres reales que le hayas puesto a tus componentes en tu archivo activity_pregunta2.xml
        val etConsumo = findViewById<EditText>(R.id.et_consumo)
        val btnCalcalcular = findViewById<Button>(R.id.btn_calcular)
        val tvResultado = findViewById<TextView>(R.id.tv_resultado)

        btnCalcalcular.setOnClickListener {
            val consumoStr = etConsumo.text.toString()
            val consumo = consumoStr.toDoubleOrNull()

            if (consumo == null || consumo < 0.0) {
                tvResultado.text = "Por favor, ingrese un valor numérico válido."
                return@setOnClickListener
            }

            if (consumo <= 150.0) {
                tvResultado.text = "Consumo eficiente sin sobrecosto."
            } else {
                val umbral = 150.0
                val exceso = consumo - umbral
                val recargoFijo = 60.0
                val costoExceso = exceso * 1.80
                val montoTotal = recargoFijo + costoExceso

                tvResultado.text = String.format(
                    "Consumo ingresado: %.2f kWh\nExceso: %.2f kWh\nMonto total a pagar por recargo: S/ %.2f",
                    consumo, exceso, montoTotal
                )
            }
        }
    }
}