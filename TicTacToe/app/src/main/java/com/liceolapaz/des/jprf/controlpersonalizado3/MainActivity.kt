package com.liceolapaz.des.jprf.controlpersonalizado3


import android.content.Intent.getIntent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var lblCasilla : TextView
    private lateinit var terTablero : TresEnRaya
    private lateinit var btnFicha : Button
    private lateinit var jugador :  TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var ganador = false
        val jugador1="Jugador 1(CRUZ)"
        val jugador2="Jugador 2(CIRCULO)"
        lblCasilla = findViewById(R.id.lblCasilla)
        jugador = findViewById(R.id.jugadorActivo)
        terTablero = findViewById(R.id.tablero)
        println(terTablero.getCasilla(0,0))

        terTablero.setOnCasillaSeleccionadaListener { fila, columna ->
            lblCasilla.text = "Última casilla seleccionada: ($fila, $columna)"

            if(jugador.text.equals(jugador1)){
                jugador.text=jugador2
            }else if(jugador.text.equals(jugador2)){
                jugador.text = jugador1
            }

            ganador=terTablero.comprobarGanador()
            if (ganador){
                jugador.text = "Ganador: "+ jugador.text
                //terTablero.setClickable(false)
                btnFicha.setVisibility(View.VISIBLE)
            }


        }

        btnFicha = findViewById(R.id.btnFicha)
        btnFicha.setOnClickListener {
           finish()
           startActivity(getIntent()) //este boton NO PUEDE  cambiar la ficha, tiene que indicar solo que jugador esta activo
        }
    }
}