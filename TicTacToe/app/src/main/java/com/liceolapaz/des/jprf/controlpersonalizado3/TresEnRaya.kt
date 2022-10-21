package com.liceolapaz.des.jprf.controlpersonalizado3

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.liceolapaz.des.jprf.controlpersonalizado3.TresEnRaya.Companion.FICHA_X

class TresEnRaya : View {
    companion object{
        const val VACIA = 0
        const val FICHA_O = 1
        const val FICHA_X = 2
    }

    private val tablero = Array(3){Array(3){0}}
    private var fichaActiva = FICHA_X
    private var xColor = Color.RED
    private var oColor = Color.BLUE
    private val pBorde = Paint().apply {
        style = Paint.Style.STROKE
        color = Color.BLACK
        strokeWidth = 4f
    }
    private val pMarcaO = Paint().apply {
        style = Paint.Style.STROKE
        strokeWidth = 10f
    }
    private val pMarcaX = Paint().apply {
        style = Paint.Style.STROKE
        strokeWidth = 10f
    }

    private lateinit var listener : OnCasillaSeleccionadaListener

    constructor(ctx: Context) : super(ctx)

    constructor(ctx: Context, attrs: AttributeSet) : super(ctx, attrs) {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.TresEnRaya, 0, 0).apply {
            try {
                oColor = getColor(R.styleable.TresEnRaya_ocolor, Color.BLUE)
                xColor = getColor(R.styleable.TresEnRaya_xcolor, Color.RED)
            } finally {
                recycle()
            }
        }
    }

    constructor(ctx: Context, attrs: AttributeSet, defStyleAttr: Int) : super(ctx, attrs, defStyleAttr) {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.TresEnRaya, 0, 0).apply {
            try {
                oColor = getColor(R.styleable.TresEnRaya_ocolor, Color.BLUE)
                xColor = getColor(R.styleable.TresEnRaya_xcolor, Color.RED)
            } finally {
                recycle()
            }
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var ancho = calcularAncho(widthMeasureSpec)
        var alto = calcularAlto(heightMeasureSpec)

        if(ancho < alto) {
            alto = ancho
        } else {
            ancho = alto
        }

        setMeasuredDimension(ancho, alto)
    }

    private fun calcularAlto(limitesSpec: Int): Int {
        var res = 100 //Alto por defecto
        val modo = MeasureSpec.getMode(limitesSpec)
        val limite = MeasureSpec.getSize(limitesSpec)

        if (modo == MeasureSpec.AT_MOST) {
            res = limite
        } else if (modo == MeasureSpec.EXACTLY) {
            res = limite
        }

        return res
    }

    private fun calcularAncho(limitesSpec: Int): Int {
        var res = 100 //Ancho por defecto
        val modo = MeasureSpec.getMode(limitesSpec)
        val limite = MeasureSpec.getSize(limitesSpec)

        if (modo == MeasureSpec.AT_MOST) {
            res = limite
        } else if (modo == MeasureSpec.EXACTLY) {
            res = limite
        }

        return res
    }

    /*fun lleno() :Boolean {
        for(i in 0..2) {
            for (j in 0..2) {
                return if(getCasilla(i,j)== FICHA_O||getCasilla(i,j)== FICHA_X){
                    true
                }else {
                    false
                }
            }}}*/


    fun setCasilla(fil: Int, col: Int, valor: Int) {
        tablero[fil][col] = valor
    }

    fun getCasilla(fil: Int, col: Int) : Int {
        return tablero[fil][col]
    }

    fun alternarFichaActiva() {
        //este metodo cambia la ficha
        fichaActiva = if (fichaActiva == FICHA_O) FICHA_X else FICHA_O
    }

    override fun onDraw(canvas: Canvas?) {
        //Obtenemos las dimensiones del control
        val alto = measuredHeight
        val ancho = measuredWidth

        //Lineas
        canvas!!.drawLine((ancho / 3).toFloat(), 0f, (ancho / 3).toFloat(), alto.toFloat(), pBorde)
        canvas.drawLine(
            (2 * ancho / 3).toFloat(), 0f,
            (2 * ancho / 3).toFloat(),
            alto.toFloat(), pBorde
        )

        canvas.drawLine(0f, (alto / 3).toFloat(), ancho.toFloat(), (alto / 3).toFloat(), pBorde)
        canvas.drawLine(
            0f,
            (2 * alto / 3).toFloat(),
            ancho.toFloat(),
            (2 * alto / 3).toFloat(), pBorde
        )

        //Marco
        canvas.drawRect(0f, 0f, ancho.toFloat(), alto.toFloat(), pBorde)

        //Marcas
        pMarcaO.color = oColor
        pMarcaX.color = xColor

        //Casillas Seleccionadas
        for (fil in 0..2) {
            for (col in 0..2) {
                if (getCasilla(fil, col) == FICHA_X) {
                    //Cruz
                    canvas.drawLine(
                        col * (ancho / 3) + ancho / 3 * 0.1f,
                        fil * (alto / 3) + alto / 3 * 0.1f,
                        col * (ancho / 3) + ancho / 3 * 0.9f,
                        fil * (alto / 3) + alto / 3 * 0.9f,
                        pMarcaX
                    )
                    canvas.drawLine(
                        col * (ancho / 3) + ancho / 3 * 0.1f,
                        fil * (alto / 3) + alto / 3 * 0.9f,
                        col * (ancho / 3) + ancho / 3 * 0.9f,
                        fil * (alto / 3) + alto / 3 * 0.1f,
                        pMarcaX
                    )
                } else if (getCasilla(fil, col) == FICHA_O) {
                    //Circulo
                    canvas.drawCircle(
                        (col * (ancho / 3) + ancho / 6).toFloat(),
                        (fil * (alto / 3) + alto / 6).toFloat(),
                        ancho / 6 * 0.8f, pMarcaO
                    )
                }
            }
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        //modificar si ya se ha seleccionado una vez no poder volver a cambiar!
        val fil = (event!!.y / (measuredHeight / 3)).toInt()
        val col = (event.x / (measuredWidth / 3)).toInt()

            //Actualizamos el tablero
        return if(getCasilla(fil,col)==FICHA_O || getCasilla(fil,col)==FICHA_X){
            false
        }else {
            setCasilla(fil, col, fichaActiva)
            alternarFichaActiva()
            //Lanzamos el evento de pulsación
            listener.onCasillaSeleccionada(fil, col);

            //Refrescamos el control
            this.invalidate()

            super.onTouchEvent(event)
        }
    }

    fun setOnCasillaSeleccionadaListener(seleccion: (Int, Int) -> Unit) {
        listener = object:OnCasillaSeleccionadaListener {
            override fun onCasillaSeleccionada(fila: Int, columna: Int) {
                seleccion(fila, columna)
            }
        }
    }
    fun comprobarGanador() :  Boolean {
        var bool = false

        if ((getCasilla(0,0)==FICHA_X||getCasilla(0,0)==FICHA_O) &&  getCasilla(0, 0) == getCasilla(0, 1) && getCasilla(0, 1) == getCasilla(0, 2)) {
            return true
        } else if ((getCasilla(1,0)==FICHA_X||getCasilla(1,0)==FICHA_O) &&  getCasilla(1, 0) == getCasilla(1, 1) && getCasilla(1, 1) == getCasilla(1, 2)) {
            return true
        } else if ((getCasilla(2,0)==FICHA_X||getCasilla(2,0)==FICHA_O) &&  getCasilla(2, 0) == getCasilla(2, 1) && getCasilla(2, 1) == getCasilla(2, 2)) {
            return true
        } else if ((getCasilla(0,0)==FICHA_X||getCasilla(0,0)==FICHA_O) &&  getCasilla(0, 0) == getCasilla(1, 0) && getCasilla(1, 0) == getCasilla(2, 0)) {
            return true
        }else if((getCasilla(0,1)==FICHA_X||getCasilla(0,1)==FICHA_O) &&  getCasilla(0,1)==getCasilla(1,1) && getCasilla(1,1)==getCasilla(2,1)) {
            return true
        }else if((getCasilla(0,2)==FICHA_X||getCasilla(0,2)==FICHA_O) &&  getCasilla(0,2)==getCasilla(1,2) && getCasilla(1,2)==getCasilla(2,2)) {
            return true
        }else if((getCasilla(0,0)==FICHA_X||getCasilla(0,0)==FICHA_O) &&  getCasilla(0,0)==getCasilla(1,1) && getCasilla(1,1)==getCasilla(2,2)) {
            return true
        }else if ((getCasilla(0,2)==FICHA_X||getCasilla(0,2)==FICHA_O) &&  getCasilla(0,2)==getCasilla(1,1) && getCasilla(1,1)==getCasilla(2,0)){
            return true
        }else if ((getCasilla(0,2)==FICHA_X||getCasilla(0,2)==FICHA_O) &&  getCasilla(0,2)==getCasilla(1,1) && getCasilla(1,1)==getCasilla(2,0)){
            return true
        }else return bool
    }
}