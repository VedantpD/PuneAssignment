package com.example.punetest.ui


import android.os.Bundle
import com.example.punetest.core.base.BaseActivity
import com.example.punetest.databinding.ActivitySplashScreenBinding

class SplashScreen : BaseActivity() {
    private lateinit var binding : ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUI()
    }

    private fun setUI() {
        val ThinkBot = "Assignment"
        val stringBuilder1 = StringBuilder()
        val t1 = Thread {
            for (letters in ThinkBot) {
                stringBuilder1.append(letters)
                Thread.sleep(250)
                runOnUiThread {
                    binding.txtTitle.text = stringBuilder1.toString()
                }
            }
            nextActivity(HomeActivity::class.java)
            finish()
        }.start()
    }
}