package pe.edu.cibertec.appgrupo1

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import pe.edu.cibertec.appgrupo1.databinding.ActivityPregunta6Binding
import java.util.Locale

class Pregunta6Activity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityPregunta6Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPregunta6Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCalcular.setOnClickListener(this)
    }

    override fun onClick(v: View?) {

        if (v?.id == binding.btnCalcular.id) {

            val horas = binding.etHoras.text.toString().toIntOrNull()

            if (horas == null || horas < 0) {
                Toast.makeText(
                    this,
                    "Ingrese una cantidad de horas válida",
                    Toast.LENGTH_SHORT
                ).show()
                return
            }

            if (horas <= 8) {

                binding.tvHorasTotales.text = ""
                binding.tvHorasExcedentes.text = ""
                binding.tvMonto.text =
                    "Horas cubiertas por la membresía mensual."

            } else {

                val horasExcedentes = horas - 8
                val monto = 50.0 + (horasExcedentes * 35.0)

                binding.tvHorasTotales.text =
                    "Horas totales: $horas"

                binding.tvHorasExcedentes.text =
                    "Horas excedentes: $horasExcedentes"

                binding.tvMonto.text =
                    "Monto a pagar: S/ %.2f".format(Locale.US, monto)
            }
        }
    }
}









