package com.adhitya.kalkulatorperhitungan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import org.mariuszgromada.math.mxparser.Expression
import java.lang.Exception
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    private lateinit var simpanData : Button
    private lateinit var hitung : TextView
    private lateinit var hasil : TextView
    private lateinit var recyclerView : RecyclerView
    private lateinit var recyclerAdapter : RecyclerView.Adapter<*>
    private lateinit var viewManager : RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        simpanData = findViewById(R.id.button_simpan)
        hitung = findViewById(R.id.hitung)
        hasil = findViewById(R.id.hasil)
        recyclerView = findViewById(R.id.listdata)

        val data = mutableListOf<Data>()
        viewManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        recyclerAdapter = Adapter(data)
        recyclerView.layoutManager = viewManager
        recyclerView.adapter = recyclerAdapter
        simpanData.setOnClickListener {
            val hitung = hitung.text.toString()
            val hasil = hasil.text.toString()
            val dataHitung = Data(hitung,hasil)
            data.add(dataHitung)
            recyclerAdapter.notifyDataSetChanged()
        }

        button_delete.setOnClickListener{
            hitung.text = ""
            hasil.text = "0"
        }
        button_0.setOnClickListener {
            hitung.text = addToInputText("0")
        }
        button_1.setOnClickListener {
            hitung.text = addToInputText("1")
        }
        button_2.setOnClickListener {
            hitung.text = addToInputText("2")
        }
        button_3.setOnClickListener {
            hitung.text = addToInputText("3")
        }
        button_4.setOnClickListener {
            hitung.text = addToInputText("4")
        }
        button_5.setOnClickListener {
            hitung.text = addToInputText("5")
        }
        button_6.setOnClickListener {
            hitung.text = addToInputText("6")
        }
        button_7.setOnClickListener {
            hitung.text = addToInputText("7")
        }
        button_8.setOnClickListener {
            hitung.text = addToInputText("8")
        }
        button_9.setOnClickListener {
            hitung.text = addToInputText("9")
        }
        button_tambah.setOnClickListener {
            hitung.text = addToInputText("+")
        }
        button_kurang.setOnClickListener {
            hitung.text = addToInputText("-")
        }
        button_kali.setOnClickListener {
            hitung.text = addToInputText("×")
        }
        button_bagi.setOnClickListener {
            hitung.text = addToInputText("÷")
        }
        button_hasil.setOnClickListener {
            showResult()
        }

    }
    private fun addToInputText(buttonValue:String):String{
        return "${hitung.text}$buttonValue"
    }

    private fun getInputExpression():String{
        var expression = hitung.text.replace(Regex("÷"),"/")
        expression = expression.replace(Regex("×"),"*")
        return expression
    }
    private fun showResult(){
        try {
            val expression = getInputExpression()
            val result = Expression(expression).calculate()
            if (result.isNaN()){
                hasil.text = "Error"
                hasil.setTextColor(ContextCompat.getColor(this,R.color.red))
            } else {
                hasil.text = DecimalFormat ("=0.######").format(result).toString()
                hasil.setTextColor(ContextCompat.getColor(this,R.color.green))
            }
        } catch (e:Exception){
            hasil.text = "Error"
            hasil.setTextColor(ContextCompat.getColor(this,R.color.red))
        }
    }
}