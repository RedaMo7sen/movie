package com.example.movies

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        textView.text = intent.getStringExtra("title")
        textView2.text = intent.getStringExtra("rate")
        textView3.text = intent.getStringExtra("ov")
    }
}