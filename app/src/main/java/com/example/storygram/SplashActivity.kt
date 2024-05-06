package com.example.storygram

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.storygram.utils.StatusBarColor
import com.example.storygram.view.BoardingActivity

class SplashActivity : AppCompatActivity() {
    companion object{
        const val SPLASH_DURATION : Long = 2000
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        StatusBarColor.change(this)

        Handler().postDelayed({
            val splashIntent = Intent(this@SplashActivity, BoardingActivity::class.java)
            startActivity(splashIntent)
            finish()
        }, SPLASH_DURATION)
    }
}