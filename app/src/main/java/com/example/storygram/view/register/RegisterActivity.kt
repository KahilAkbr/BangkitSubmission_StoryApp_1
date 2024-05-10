package com.example.storygram.view.register

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.storygram.data.Result
import com.example.storygram.databinding.ActivityRegisterBinding
import com.example.storygram.utils.MotionVisibility.Companion.setMotionVisibilities
import com.example.storygram.utils.ObtainViewModelFactory
import com.example.storygram.view.login.LoginActivity
class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        overridePendingTransition(androidx.appcompat.R.anim.abc_slide_in_top, 0)

        val viewModel = ObtainViewModelFactory.obtain<RegisterViewModel>(this)

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
                            binding.progressBar.setMotionVisibilities(View.VISIBLE)
                            Log.d("BJIR", name)
                        }
                        is Result.Success -> {
                            binding.progressBar.setMotionVisibilities(View.GONE)
                            Toast.makeText(this, "Berhasil", Toast.LENGTH_SHORT).show()
                        }
                        is Result.Error -> {
                            binding.progressBar.setMotionVisibilities(View.GONE)
                            Toast.makeText(this, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }
}