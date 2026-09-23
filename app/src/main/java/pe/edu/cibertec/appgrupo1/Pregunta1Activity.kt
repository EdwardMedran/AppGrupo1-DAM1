package pe.edu.cibertec.appgrupo1

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import pe.edu.cibertec.appgrupo1.databinding.ActivityPregunta1Binding

class Pregunta1Activity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityPregunta1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPregunta1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCalcular.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v?.id == binding.btnCalcular.id) {
            calcularConsumoAgua()
        }
    }

    private fun calcularConsumoAgua() {
        val input = binding.etVolumen.text.toString()
        if (input.isEmpty()) {
            binding.etVolumen.error = "Ingrese un valor"
            return
        }

        val volumen = input.toDoubleOrNull() ?: 0.0

        if (volumen <= 20) {
            binding.tvResultado.text = "Consumo dentro de la asignación regular."
        } else {
            val exceso = volumen - 20
            val recargo = 45.00 + (8.50 * exceso)

            val montoFormateado = String.format("S/ %.2f", recargo)

            binding.tvResultado.text = """
                Volumen consumido: $volumen m³
                Exceso: $exceso m³
                Monto total del recargo: $montoFormateado
            """.trimIndent()
        }
    }
}