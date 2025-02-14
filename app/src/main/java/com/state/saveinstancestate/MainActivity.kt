package com.state.saveinstancestate

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    lateinit var countViewModel: CountViewModel
    private lateinit var messageTV: TextView
    private lateinit var inputET : EditText
    private lateinit var buttonBTN: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        countViewModel = ViewModelProvider(this)[CountViewModel::class.java]

        messageTV = findViewById(R.id.outMessage)
        inputET = findViewById(R.id.inputET)
        buttonBTN = findViewById(R.id.inputB)

        countViewModel.currentNumber.observe(this, Observer {
            messageTV.text = it.toString()
        })

        buttonBTN.setOnClickListener{
            countViewModel.currentNumber.value = ++countViewModel.number
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    }
    override fun onSaveInstanceState(outState: Bundle) {
        outState?.run {
            putString("key", messageTV.text.toString())
        }
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        messageTV.text = savedInstanceState.getString("key")
    }
}