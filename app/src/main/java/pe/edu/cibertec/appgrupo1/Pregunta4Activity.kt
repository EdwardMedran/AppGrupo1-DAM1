package pe.edu.cibertec.appgrupo1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Locale

class Pregunta4Activity : AppCompatActivity() {

    private lateinit var etConsumoGb: EditText
    private lateinit var btnCalcular: Button
    private lateinit var tvResultado: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pregunta4)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        etConsumoGb = findViewById(R.id.et_consumo_gb)
        btnCalcular = findViewById(R.id.btn_calcular)
        tvResultado = findViewById(R.id.tv_resultado)

        btnCalcular.setOnClickListener {
            calcularPlanDatos()
        }
    }

    private fun calcularPlanDatos() {
        val inputStr = etConsumoGb.text.toString()

        if (inputStr.isEmpty()) {
            etConsumoGb.error = "Por favor ingrese los gigabytes consumidos"
            return
        }

        val gigabytesConsumidos = inputStr.toDoubleOrNull()
        if (gigabytesConsumidos == null || gigabytesConsumidos < 0) {
            etConsumoGb.error = "Ingrese un valor numérico válido"
            return
        }

        val paqueteBase = 30.0

        if (gigabytesConsumidos <= paqueteBase) {
            tvResultado.text = "Consumo dentro de su plan contratado."
        } else {
            // Caso 2: Exceso de datos
            val excesoGb = gigabytesConsumidos - paqueteBase
            val cobroAdicional = 25.0 + (excesoGb * 6.0)

            val resultadoTexto = String.format(
                Locale.US,
                "Datos consumidos: %.2f GB\nExceso de GB: %.2f GB\nCobro adicional generado: S/ %.2f",
                gigabytesConsumidos,
                excesoGb,
                cobroAdicional
            )
            tvResultado.text = resultadoTexto
        }
    }
}