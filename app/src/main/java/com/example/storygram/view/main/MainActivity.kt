package com.example.storygram.view.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.storygram.R
import com.example.storygram.databinding.ActivityMainBinding
import com.example.storygram.utils.ObtainViewModelFactory
import com.example.storygram.view.boarding.BoardingActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel = ObtainViewModelFactory.obtain<MainViewModel>(this)

        binding.button.setOnClickListener {
            viewModel.logout()
            val intent = Intent(this, BoardingActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finishAffinity()
            Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show()
        }
    }
}