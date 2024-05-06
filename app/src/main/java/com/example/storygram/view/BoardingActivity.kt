package com.example.storygram.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.storygram.R
import com.example.storygram.utils.StatusBarColor

class BoardingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_boarding)

        StatusBarColor.change(this)


    }
}