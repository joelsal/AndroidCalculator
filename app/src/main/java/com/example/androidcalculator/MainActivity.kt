package com.example.androidcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        one.setOnClickListener {appendOnExpression("1", true)}
        two.setOnClickListener {appendOnExpression("2", true)}
        three.setOnClickListener {appendOnExpression("3", true)}
        four.setOnClickListener {appendOnExpression("4", true)}
        five.setOnClickListener {appendOnExpression("5", true)}
        six.setOnClickListener {appendOnExpression("6", true)}
        seven.setOnClickListener {appendOnExpression("7", true)}
        eight.setOnClickListener {appendOnExpression("8", true)}
        nine.setOnClickListener {appendOnExpression("9", true)}
        zero.setOnClickListener {appendOnExpression("0", true)}

        plus.setOnClickListener {appendOnExpression("+", false)}
        minus.setOnClickListener {appendOnExpression("-", false)}
        multiply.setOnClickListener {appendOnExpression("*", false)}
        divide.setOnClickListener {appendOnExpression("/", false)}

        open.setOnClickListener {appendOnExpression("(", false)}
        close.setOnClickListener {appendOnExpression(")", false)}

        clear.setOnClickListener {
            input_field.text = ""
            view_result.text = ""
        }

        erase.setOnClickListener {
            val string = input_field.text.toString()
            if(string.isNotEmpty()) {
                input_field.text = string.substring(0,string.length-1)
            }
        }

        equals.setOnClickListener {
            try {
                val expression = ExpressionBuilder(input_field.text.toString()).build()
                val result = expression.evaluate()
                val longResult = result.toLong()
                if (result == longResult.toDouble()) {
                    view_result.text = longResult.toString()
                } else {
                    view_result.text = result.toString()
                }
            } catch (e:Exception) {
                Log.d("Exception", " message : " + e.message)
            }
        }

    }

    fun appendOnExpression(string: String, canClear: Boolean) {

        if (view_result.text.isNotEmpty()) {
            input_field.text = ""
        }

        if (canClear) {
            view_result.text = ""
            input_field.append(string)
        } else {
            input_field.append(view_result.text)
            input_field.append(string)
            view_result.text = ""
        }
    }
}
