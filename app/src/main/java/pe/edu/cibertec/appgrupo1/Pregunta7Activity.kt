package pe.edu.cibertec.appgrupo1

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import pe.edu.cibertec.appgrupo1.databinding.ActivityPregunta7Binding
import java.util.Locale

class Pregunta7Activity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityPregunta7Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPregunta7Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCalcular.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v?.id == binding.btnCalcular.id) {
            evaluarTemperatura()
        }
    }

    private fun evaluarTemperatura() {
        val input = binding.etTemperatura.text.toString()

        if (input.isEmpty()) {
            binding.tvResultado.text = "Por favor, ingrese la temperatura medida."
            return
        }

        val temperatura = input.toDoubleOrNull() ?: 0.0

        if (temperatura <= 35.0) {
            binding.tvResultado.text = "Parámetro térmico en norma ambiental."
        } else {
            val excesoTermico = temperatura - 35.0
            val sancionTotal = 3200.00 + (450.00 * excesoTermico)

            val sancionFormateada = String.format(Locale.US, "%.2f", sancionTotal)
            val excesoFormateado = String.format(Locale.US, "%.2f", excesoTermico)

            val resultado = """
                Temperatura registrada: $temperatura °C
                Exceso térmico: $excesoFormateado °C
                Sanción total calculada: S/ $sancionFormateada
            """.trimIndent()

            binding.tvResultado.text = resultado
        }
    }
}