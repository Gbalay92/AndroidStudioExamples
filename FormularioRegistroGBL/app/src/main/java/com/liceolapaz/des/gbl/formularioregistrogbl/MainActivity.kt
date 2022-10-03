package com.liceolapaz.des.gbl.formularioregistrogbl

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import java.util.*


class MainActivity() : AppCompatActivity() {
    private lateinit var inputPais: Spinner
    private lateinit var name: EditText
    private lateinit var secondName: EditText
    private lateinit var mail: EditText
    private lateinit var password: EditText
    private lateinit var password2: EditText
    private lateinit var sexo: RadioGroup
    private lateinit var fecha: EditText
    private lateinit var phone: EditText
    private lateinit var btnRegister: Button
    private lateinit var txtExit: TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inputPais = findViewById(R.id.inputPais)
        val adaptador =
            ArrayAdapter.createFromResource(
                this, R.array.array_pais,
                android.R.layout.simple_spinner_item
            )
        adaptador.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )
        inputPais.adapter = adaptador



        name = findViewById(R.id.inputName)
        secondName = findViewById(R.id.inputAp)
        phone = findViewById(R.id.inputPhone)
        mail = findViewById(R.id.inputMail)
        password2 = findViewById(R.id.inputPassword2)
        password = findViewById(R.id.inputPassword)
        sexo = findViewById(R.id.grbGrupo1)
        var genero=recuperarGenero(sexo)
        fecha = findViewById(R.id.inputFecha)
        btnRegister = findViewById(R.id.btnReg)
        txtExit = findViewById(R.id.txtExit)
        var fechaPut = extraerFecha(fecha)



        btnRegister.setOnClickListener {
            if (password.text.toString() != password2.text.toString()) {

                txtExit.text = "las contraseñas deben ser iguales"

            } else if (name.text.isEmpty() || secondName.text.isEmpty() || password.text.isEmpty() || mail.text.isEmpty() || sexo.checkedRadioButtonId == -1) {
                txtExit.text = "Los campos obligatorios deben ser rellenados"

            } else {

                val intent = Intent(this@MainActivity, ShowInformation::class.java)


                intent.putExtra("NAME", name.text.toString())
                intent.putExtra("SNAME", secondName.text.toString())
                intent.putExtra("PHONE", phone.text.toString())
                intent.putExtra("MAIL", mail.text.toString())
                intent.putExtra("SEX", genero.toString())
                intent.putExtra("FECHA", fechaPut.toString())

                startActivity(intent)
            }

        }

    }

    private fun recuperarGenero(sexo: RadioGroup?): Toast? {
        val genero: RadioButton? = sexo?.let { findViewById(it.checkedRadioButtonId) }
        if (genero != null) {
            return Toast.makeText(applicationContext, genero.text, Toast.LENGTH_SHORT)
        }
            return null
    }
    private fun extraerFecha(fecha: EditText) : String {
        var cal = Calendar.getInstance()
        var year = cal.get(Calendar.YEAR)
        var month = cal.get(Calendar.MONTH)
        var dayOfMonth = cal.get(Calendar.DAY_OF_MONTH)

        fecha.setOnClickListener {
            var monthr=month+1
            val datePickerDialog = DatePickerDialog(
                this,
                { view, year, month, dayOfMonth ->
                    fecha.setText("$dayOfMonth/$monthr/$year")
                },
                year,
                month,
                dayOfMonth
            )

            datePickerDialog.show()

        }

        return "$dayOfMonth/$month/$year"
    }


    }

