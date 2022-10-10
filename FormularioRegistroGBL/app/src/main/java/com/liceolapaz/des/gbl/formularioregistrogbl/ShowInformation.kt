package com.liceolapaz.des.gbl.formularioregistrogbl

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ShowInformation : AppCompatActivity() {
    private lateinit var  name: TextView
    private lateinit var secondName : TextView
    private lateinit var mail : TextView
    private lateinit var sexo : TextView
    private lateinit var fecha : TextView
    private lateinit var phone : TextView
    private lateinit var boletin : TextView
    private lateinit var pais : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_information)

        name=findViewById(R.id.showName)
        secondName=findViewById(R.id.showAp)
        mail=findViewById(R.id.showMail)
        sexo=findViewById(R.id.showSex)
        fecha=findViewById(R.id.showFecha)
        phone=findViewById(R.id.showPhone)
        pais=findViewById(R.id.showPais)

        name.text="Nombre: " + intent.getStringExtra("NAME")
        secondName.text="Apellidos: "+ intent.getStringExtra("SNAME")
        mail.text="Email: "+ intent.getStringExtra("MAIL")
        sexo.text="Genero: "+ intent.getStringExtra("SEX")
        fecha.text="Fecha de nacimiento: "+ intent.getStringExtra("FECHA")
        phone.text="Teléfono: "+ intent.getStringExtra("PHONE")
        pais.text="Pais: "+ intent.getStringExtra("PAIS")





    }
}