package com.example.storygram.view.register

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.lifecycle.ViewModelProvider
import com.example.storygram.data.Result
import com.example.storygram.databinding.ActivityRegisterBinding
import com.example.storygram.utils.MotionVisibility.Companion.setVisibilities
import com.example.storygram.utils.ObtainViewModelFactory
import com.example.storygram.view.login.LoginActivity
import com.example.storygram.view.viewmodelfactory.ViewModelFactory

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    private var isLoading = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        overridePendingTransition(androidx.appcompat.R.anim.abc_slide_in_top, 0)

        val factory: ViewModelFactory =
            ViewModelFactory.getInstance(
                this
            )
        val viewModel: RegisterViewModel = ViewModelProvider(this,factory)[RegisterViewModel::class.java]

        binding.toLogin.setOnClickListener {
            intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.btnSignup.setOnClickListener {
            val name = binding.usernameEditText.text.toString()
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            viewModel.register(name, email, password).observe(this){result ->
                if(result != null) {
                    when(result){
                        is Result.Loading -> {
                            binding.progressBar.setVisibilities(View.VISIBLE)
                            Log.d("BJIR", name)
                        }
                        is Result.Success -> {
                            binding.progressBar.setVisibilities(View.GONE)
                            Toast.makeText(this, "Berhasil", Toast.LENGTH_SHORT).show()
                        }
                        is Result.Error -> {
                            binding.progressBar.setVisibilities(View.GONE)
                            Toast.makeText(this, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }
}