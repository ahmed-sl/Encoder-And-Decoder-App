package com.example.encoderanddecoder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var entEncod: EditText
    lateinit var entDncod: EditText

    lateinit var btnEncod: Button
    lateinit var btnDncod: Button

    lateinit var rcv : RecyclerView
    lateinit var rvAD: rvAdapter
     val liteles = "abcdefghijklmnopqrstuvwxyz"
    lateinit var codes : ArrayList<Pharse>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        codes = arrayListOf()

        rcv = findViewById(R.id.rcv)
        rvAD = rvAdapter(codes)
        rcv.adapter = rvAD
        rcv.layoutManager= LinearLayoutManager(this)

        entEncod= findViewById(R.id.entEncod)
        entDncod= findViewById(R.id.entDncod)

        btnEncod= findViewById(R.id.btnEncod)
        btnEncod.setOnClickListener { processText(true) }
        btnDncod= findViewById(R.id.btnDncod)
        btnDncod.setOnClickListener { processText(false) }

    }
    private fun processText(encode: Boolean){
        var output = ""
        var pos = 0
        if(encode){
            if(!entEncod.text.isNullOrEmpty()){
                for(letter in entEncod.text.toString()){
                    if(liteles.indexOf(letter) < 0){
                        if(liteles.uppercase().indexOf(letter) < 0){
                            output += letter
                        }else{
                            pos = liteles.uppercase().indexOf(letter) + 13
                            if(pos > 25){
                                pos -= 26
                            }
                            output += liteles.uppercase()[pos]
                        }
                    }else{
                        pos = liteles.indexOf(letter) + 13
                        if(pos > 25){
                            pos -= 26
                        }
                        output += liteles[pos]
                    }
                }
                codes.add(Pharse(entEncod.text.toString(), true))
                entEncod.text.clear()
            }else{
                Toast.makeText(this, "Phrase cannot be empty", Toast.LENGTH_LONG).show()
            }
        }else{
            if(!entDncod.text.isNullOrEmpty()){
                for(letter in entDncod.text.toString()){
                    if(liteles.indexOf(letter) < 0){
                        if(liteles.uppercase().indexOf(letter) < 0){
                            output += letter
                        }else{
                            pos = liteles.uppercase().indexOf(letter) - 13
                            if(pos < 0){
                                pos += 26
                            }
                            output += liteles.uppercase()[pos]
                        }
                    }else{
                        pos = liteles.indexOf(letter) - 13
                        if(pos < 0){
                            pos += 26
                        }
                        output += liteles[pos]
                    }
                }
                codes.add(Pharse(entDncod.text.toString(), true))
                entDncod.text.clear()
            }else{
                Toast.makeText(this, "Phrase cannot be empty", Toast.LENGTH_LONG).show()
            }
        }
        codes.add(Pharse(output, false))
        rvAD.update()
    }
}