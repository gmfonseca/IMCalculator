package gmfonseca.com.br.imcalculator

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.text.DecimalFormat
import kotlin.math.pow

class MainActivity : AppCompatActivity() {

    private var alturaInput: EditText? = null
    private var pesoInput: EditText? = null

    private var calcButton: Button? = null

    private var imcClass: TextView? = null
    private var imcText: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initComponents()

        calcButton?.setOnClickListener {

            val imc = calculateIMC(java.lang.Double.parseDouble("${alturaInput?.text}"), java.lang.Double.parseDouble("${pesoInput?.text}"))
            val classificacao = imcClassification(imc)

            if(imc != -1.0) {
                val df = DecimalFormat("#.##")
                imcClass?.setText("${classificacao}")
                imcText?.setText("IMC: ${df.format(imc)}")
            }
            else
                Toast.makeText(applicationContext, "Informe um valor válido.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun initComponents(){
        alturaInput = findViewById(R.id.alturaInput)
        pesoInput = findViewById(R.id.pesoInput)
        imcClass = findViewById(R.id.imcClass)
        imcText = findViewById(R.id.imcResult)
        calcButton = findViewById(R.id.botao)
    }

    private fun calculateIMC(height: Double, weight: Double) : Double {
        var imc = -1.0

        if(height < 5 && height > 0)
            imc = weight / (height.pow(2))
        else if(height > 5)
            imc = weight / ((height/100).pow(2))

        return imc
    }

    private fun imcClassification(imc: Double) : String{
        val classification: String

        if(imc < 16) classification = "Magreza grave"
        else if(16 < imc && imc < 17) classification = "Magreza moderada"
        else if(17 < imc && imc < 18.5) classification = "Magreza Leve"
        else if(18.5 < imc && imc < 25) classification = "Saudável"
        else if(25 < imc && imc < 30) classification = "Sobrepeso"
        else if(30 < imc && imc < 35) classification = "Obesidade Grau I"
        else if(35 < imc && imc < 40) classification = "Obesidade Grau II (severa)"
        else classification = "Obesidade Grau III (mórbida)"

        return classification
    }
}
