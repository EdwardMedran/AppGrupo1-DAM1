package pe.edu.cibertec.appgrupo1
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import pe.edu.cibertec.appgrupo1.databinding.ActivityPregunta5Binding
// Heredamos AppCompat e implementamos la interfaz del click como pide la rúbrica
class Pregunta5Activity : AppCompatActivity(), View.OnClickListener {

    // Variable para el binding (para olvidarnos del clásico findViewById)
    private lateinit var binding: ActivityPregunta5Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflamos la vista y la seteamos
        binding = ActivityPregunta5Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // Enganchamos el botón con la interfaz de la propia clase
        binding.btnProcesarCobro.setOnClickListener(this)
    }

    // Método obligatorio de la interfaz
    override fun onClick(vista: View?) {
        // Validamos que el click venga de nuestro botón específico
        if (vista?.id == binding.btnProcesarCobro.id) {
            sacarCuenta()
        }
    }

    private fun sacarCuenta() {
        // Capturamos lo que escribió el usuario
        val textoBolsas = binding.edtCantidadBolsas.text.toString()

        // Filtro básico por si le dan click en vacío
        if (textoBolsas.isNotEmpty()) {

            // Lo parseamos a entero ya que son bolsas enteras
            val cantidadBolsas = textoBolsas.toInt()

            if (cantidadBolsas <= 5) {
                // Dentro del límite permitido, no pagan extra
                binding.tvSalidaFinal.text = "Carga permitida sin costo adicional."
            } else {
                // Se pasaron del límite, toca calcular el sobrecargo
                val exceso = cantidadBolsas - 5
                val totalPagar = 80.00 + (exceso * 15.00)

                // Formateamos para que salga como moneda con sus 2 decimales
                val monedaFormateada = String.format("S/ %.2f", totalPagar)

                // Armamos el texto final con los saltos de línea requeridos
                binding.tvSalidaFinal.text = "Cantidad ingresada: $cantidadBolsas\n" +
                        "Exceso: $exceso\n" +
                        "Total a abonar por el servicio: $monedaFormateada"
            }
        } else {
            Toast.makeText(this, "Ingresa un número de bolsas primero", Toast.LENGTH_SHORT).show()
        }
    }
}