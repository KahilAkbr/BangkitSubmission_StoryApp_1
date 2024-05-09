package com.example.storygram.view.register

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.example.storygram.R
import com.example.storygram.databinding.ActivityRegisterBinding
import com.example.storygram.view.login.LoginActivity

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        overridePendingTransition(androidx.appcompat.R.anim.abc_slide_in_top, 0)

        binding.toLogin.setOnClickListener {
            intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}