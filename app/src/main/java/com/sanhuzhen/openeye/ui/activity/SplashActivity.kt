package com.sanhuzhen.openeye.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.CountDownTimer
import com.sanhuzhen.openeye.R
import com.sanhuzhen.openeye.base.BaseActivity
import com.sanhuzhen.openeye.databinding.ActivitySplashBinding

/**
 * @author sanhuzhen
 * @since 2024/6/1
 * @description 启动页
 */
@SuppressLint("CustomSplashScreen")
class SplashActivity: BaseActivity<ActivitySplashBinding>() {
    private lateinit var countDownTimer: CountDownTimer
    override fun initView() {
        initEvent()
    }

    override fun initBinding(): ActivitySplashBinding {
        return ActivitySplashBinding.inflate(layoutInflater)
    }

    private fun initEvent() {
        binding.tvSplash.setOnClickListener {
            startActivity(Intent(this@SplashActivity,MainActivity::class.java))
            finish()
        }
        countDownTimer = object : CountDownTimer(3000, 1000) {
            @SuppressLint("SetTextI18n", "ResourceAsColor")
            override fun onTick(millisUntilFinished: Long) {
                binding.tvSplash.text = "跳过,${millisUntilFinished / 1000}s"
            }
            override fun onFinish() {
                startActivity(Intent(this@SplashActivity,MainActivity::class.java))
                finish()
            }
        }.start()
    }
    override fun onDestroy() {
        super.onDestroy()
        countDownTimer.cancel()
    }
}