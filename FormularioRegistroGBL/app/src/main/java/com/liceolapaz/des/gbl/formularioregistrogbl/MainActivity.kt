package com.liceolapaz.des.gbl.formularioregistrogbl

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.mouredev.des.gbl.formularioregistrogbl.R

class MainActivity : AppCompatActivity() {
    private lateinit var inputPais : Spinner
    private lateinit var  name: EditText
    private lateinit var secondName :EditText
    private lateinit var mail : EditText
    private lateinit var password : EditText
    private lateinit var password2 : EditText
    private lateinit var sexo : EditText
    private lateinit var fecha : EditText
    private lateinit var phone : EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inputPais = findViewById(R.id.inputPais)
        val adaptador =
            ArrayAdapter.createFromResource(this, R.array.array_pais,
                android.R.layout.simple_spinner_item)
        adaptador.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item)
        inputPais.adapter = adaptador



        name=findViewById(R.id.inputName)
        secondName=findViewById(R.id.inputAp)
        phone=findViewById(R.id.inputPhone)
        mail=findViewById(R.id.inputMail)
        password2=findViewById(R.id.inputPassword2)
        password=findViewById(R.id.inputPassword)
        sexo=findViewById(R.id.grbGrupo1)
        fecha









    }
}