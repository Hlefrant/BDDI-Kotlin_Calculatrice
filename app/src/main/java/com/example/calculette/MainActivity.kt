package com.example.calculette

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.children
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception


class MainActivity : AppCompatActivity() {

    lateinit var result:TextView
    lateinit var input:TextView
    lateinit var str:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        initView()

    }

    private fun initView(){
        val parent = findViewById<ConstraintLayout>(R.id.parentView)
        val buttons:Sequence<View> = parent.children.filter {it is Button}
        
        buttons.forEach {
            it.setOnClickListener{
                result = findViewById(R.id.result)
                input = findViewById(R.id.input)
                str = input.text.toString()
                when (it.tag){
                    "number"->{
                            if (it is Button){
                                input.text = (str + it.text)
                            }
                    }
                    "operation"->{
                            if (it is Button){
                                input.text = (str + it.text)
                            }
                    }
                    "clear"->{
                        if (it is Button){
                            input.text = ""
                            result.text = ""
                        }
                    }
                    "result"->{
                        try {
                            val inputTotal = ExpressionBuilder(input.text.toString()).build()
                            val output = inputTotal.evaluate()
                            val longOutput = output.toLong()

                            if (output == longOutput.toDouble()){
                                result.text = longOutput.toString()
                            }else{
                                result.text = output.toString()
                            }

                        }catch(e:Exception) {
                            Log.d("Error", e.toString())
                        }

                    }
                }
            }

        }
    }
}
