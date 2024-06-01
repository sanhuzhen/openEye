package com.sanhuzhen.openeye.base

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.sanhuzhen.openeye.utils.Density


abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {
    protected lateinit var binding: VB
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Density.setDensity(this, application)
        binding = initBinding()
        setContentView(binding.root)
        initView()
        hideStatusBar()
    }

    abstract fun initView()
    abstract fun initBinding(): VB

//    //将状态栏设置为透明，扒的掌邮
//    private fun cancelStatusBar() {
//        val window = this.window
//        val decorView = window.decorView
//
//        // 这是 Android 做了兼容的 Compat 包
//        // 注意，使用了下面这个方法后，状态栏不会再有东西占位，
//        // 可以给根布局加上 android:fitsSystemWindows=true
//        // 不同布局该属性效果不同，请给合适的布局添加
//        WindowCompat.setDecorFitsSystemWindows(window, false)
//        val windowInsetsController = WindowCompat.getInsetsController(window, decorView)
//        // 如果你要白色的状态栏字体，请在你直接的 Activity 中单独设置 isAppearanceLightStatusBars，这里不提供方法
//        windowInsetsController.isAppearanceLightStatusBars = true
//        window.statusBarColor = Color.TRANSPARENT //把状态栏颜色设置成透明
//    }
    //隐藏状态栏
    private fun hideStatusBar(){
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}