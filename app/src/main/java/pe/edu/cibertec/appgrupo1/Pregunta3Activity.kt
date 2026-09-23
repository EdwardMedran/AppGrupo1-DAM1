package pe.edu.cibertec.appgrupo1

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import pe.edu.cibertec.appgrupo1.databinding.ActivityPregunta3Binding
import java.util.Locale

class Pregunta3Activity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityPregunta3Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPregunta3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCalcular.setOnClickListener(this)
    }

    override fun onClick(v: View?) {

        if (v?.id == binding.btnCalcular.id) {

            val tiempoTexto = binding.etTiempo.text.toString()

            if (tiempoTexto.isEmpty()) {
                Toast.makeText(
                    this,
                    "Ingrese el tiempo estacionado",
                    Toast.LENGTH_SHORT
                ).show()
                return
            }

            val tiempo = tiempoTexto.toInt()

            if (tiempo < 0) {
                Toast.makeText(
                    this,
                    "El tiempo no puede ser negativo",
                    Toast.LENGTH_SHORT
                ).show()
                return
            }

            if (tiempo <= 60) {

                binding.tvResultado.text =
                    "Estacionamiento cubierto por periodo de cortesía."

            } else {

                val exceso = tiempo - 60

                val monto = 10.00 + (exceso * 0.50)

                binding.tvResultado.text = String.format(
                    Locale.US,
                    "Tiempo total registrado: %d minutos\n" +
                            "Minutos de exceso: %d minutos\n" +
                            "Monto total a pagar: S/ %.2f",
                    tiempo,
                    exceso,
                    monto
                )
            }
        }
    }
}