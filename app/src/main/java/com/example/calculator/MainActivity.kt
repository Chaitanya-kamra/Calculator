package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private var input :TextView? = null
    var lastDecimal = false
    var lastDigit = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        input = findViewById(R.id.textView)
    }

    fun onButtonClick(view: View){
        input?.append((view as Button).text)
        lastDigit = true
        lastDecimal = false
    }

    fun onclear(view: View){
        input?.text=""
    }

    fun onDecimal(view:View){
        if (lastDigit && !lastDecimal){
            input?.append(".")
            lastDecimal = true
            lastDigit = false
        }
    }
    fun onOperator(view: View){
        input?.text?.let {
            if (lastDigit && !isOperaterAdded(it.toString())){
                input?.append((view as Button).text)
                lastDigit = false
                lastDecimal= false
            }
        }

    }
    private fun isOperaterAdded(value: String): Boolean{
        return if (value.startsWith("-")){
            false
        }else{
            value.contains("+") || value.contains("*") || value.contains("-") || value.contains("/")
        }
    }
    fun onEqual(view: View){
        if (lastDigit){
            var textValue = input?.text.toString()
            var prefix = ""
            try {
                if (textValue.startsWith("-")){
                    prefix = "-"
                    textValue = textValue.substring(1)
                }
                if (textValue.contains("-")) {
                    val splitvalue = textValue.split("-")
                    var one = splitvalue[0]
                    var two = splitvalue[1]
                    if (prefix.isNotEmpty()) {
                        one = prefix + one
                    }
                    input?.text = (one.toDouble() - two.toDouble()).toString()
                }else if (textValue.contains("+")) {
                    val splitvalue = textValue.split("+")
                    var one = splitvalue[0]
                    var two = splitvalue[1]
                    if (prefix.isNotEmpty()) {
                        one = prefix + one
                    }
                    input?.text = (one.toDouble() + two.toDouble()).toString()
                }else if (textValue.contains("/")) {
                    val splitvalue = textValue.split("/")
                    var one = splitvalue[0]
                    var two = splitvalue[1]
                    if (prefix.isNotEmpty()) {
                        one = prefix + one
                    }
                    if (two == "0"){
                        input?.text = "Infinity"
                    }else{
                    input?.text = (one.toDouble() / two.toDouble()).toString()
                    }
                }
                else if (textValue.contains("*")) {
                    val splitvalue = textValue.split("*")
                    var one = splitvalue[0]
                    var two = splitvalue[1]
                    if (prefix.isNotEmpty()) {
                        one = prefix + one
                    }
                    input?.text = (one.toDouble() * two.toDouble()).toString()
                }
            }
            catch (e: ArithmeticException){
                e.printStackTrace()
            }
        }
        else{
            Toast.makeText(this,"Enter Number",Toast.LENGTH_SHORT).show()
        }
    }


}