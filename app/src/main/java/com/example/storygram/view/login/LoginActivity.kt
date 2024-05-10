package com.example.storygram.view.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.storygram.data.Result
import com.example.storygram.databinding.ActivityLoginBinding
import com.example.storygram.utils.MotionVisibility.Companion.setMotionVisibilities
import com.example.storygram.utils.ObtainViewModelFactory
import com.example.storygram.view.main.MainActivity
import com.example.storygram.view.register.RegisterActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        overridePendingTransition(androidx.appcompat.R.anim.abc_slide_in_top, 0)

        val viewModel = ObtainViewModelFactory.obtain<LoginViewModel>(this)

        binding.toSignUp.setOnClickListener {
            intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.btnLogin.setOnClickListener {
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            viewModel.login(email, password).observe(this){result ->
                if(result != null){
                    when(result){
                        is Result.Loading -> {
                            binding.progressBar.setMotionVisibilities(View.VISIBLE)
                        }
                        is Result.Success -> {
                            binding.progressBar.setMotionVisibilities(View.GONE)
                            viewModel.saveToken(result.data.loginResult.token)
                            Toast.makeText(this, "Berhasil", Toast.LENGTH_SHORT).show()

                            val intent = Intent(this, MainActivity::class.java)
                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            startActivity(intent)
                            finishAffinity()
                        }
                        is Result.Error -> {
                            binding.progressBar.setMotionVisibilities(View.GONE)
                            Toast.makeText(this, "Gagal", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }

    }
}