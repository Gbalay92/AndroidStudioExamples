package com.liceolapaz.des.gbl.holausuario

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class SaludoActivity : AppCompatActivity() {
    private lateinit var txtSaludo : TextView


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saludo)

        txtSaludo = findViewById(R.id.txtSaludo)

        val saludo = intent.getStringExtra("NOMBRE")

        txtSaludo.text = "Hola $saludo !!"

    }
}