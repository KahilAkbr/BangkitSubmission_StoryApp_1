package com.example.storygram.view.setting

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.storygram.R
import com.example.storygram.databinding.ActivitySettingBinding
import com.example.storygram.utils.ObtainViewModelFactory
import com.example.storygram.view.boarding.BoardingActivity
import com.example.storygram.view.main.MainViewModel

class SettingActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySettingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel = ObtainViewModelFactory.obtain<SettingViewModel>(this)

        binding.button2.setOnClickListener {
            viewModel.logout()
            val intent = Intent(this, BoardingActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finishAffinity()
            Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show()
        }
    }
}