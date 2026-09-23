package pe.edu.cibertec.appgrupo1
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import pe.edu.cibertec.appgrupo1.databinding.ActivityPregunta5Binding
class Pregunta5Activity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityPregunta5Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPregunta5Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnProcesarCobro.setOnClickListener(this)
    }

    override fun onClick(vista: View?) {
        if (vista?.id == binding.btnProcesarCobro.id) {
            sacarCuenta()
        }
    }

    private fun sacarCuenta() {
        val textoBolsas = binding.edtCantidadBolsas.text.toString()

        if (textoBolsas.isNotEmpty()) {

            val cantidadBolsas = textoBolsas.toInt()
            if (cantidadBolsas <= 5) {
                binding.tvSalidaFinal.text = "Carga permitida sin costo adicional."
            } else {
                val exceso = cantidadBolsas - 5
                val totalPagar = 80.00 + (exceso * 15.00)
                val monedaFormateada = String.format("S/ %.2f", totalPagar)

                binding.tvSalidaFinal.text = "Cantidad ingresada: $cantidadBolsas\n" +
                        "Exceso: $exceso\n" +
                        "Total a abonar por el servicio: $monedaFormateada"
            }
        } else {
            Toast.makeText(this, "Ingresa un número de bolsas primero", Toast.LENGTH_SHORT).show()
        }
    }
}