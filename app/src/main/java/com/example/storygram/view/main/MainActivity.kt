package com.example.storygram.view.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.storygram.R
import com.example.storygram.data.Result
import com.example.storygram.databinding.ActivityMainBinding
import com.example.storygram.utils.MotionVisibility.Companion.setMotionVisibilities
import com.example.storygram.utils.ObtainViewModelFactory
import com.example.storygram.view.boarding.BoardingActivity
import com.example.storygram.view.login.LoginActivity
import com.example.storygram.view.setting.SettingActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel = ObtainViewModelFactory.obtain<MainViewModel>(this)

        binding.setting.setOnClickListener {
            val intent = Intent(this, SettingActivity::class.java)
            startActivity(intent)
        }

        viewModel.getAllStory().observe(this){result ->
            if(result != null) {
                when(result){
                    is Result.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is Result.Success -> {
                        binding.progressBar.visibility = View.GONE
                        Log.d("BJIR", result.toString())

                    }
                    is Result.Error -> {
                        binding.progressBar.visibility = View.GONE
                        Log.d("BJIR", result.error)

                    }
                }
            }
        }
    }
}