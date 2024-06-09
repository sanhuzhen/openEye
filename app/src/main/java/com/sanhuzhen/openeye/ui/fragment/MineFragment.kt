package com.sanhuzhen.openeye.ui.fragment

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.tabs.TabLayoutMediator
import com.sanhuzhen.openeye.adapter.ViewPaperAdapter
import com.sanhuzhen.openeye.base.BaseFragment
import com.sanhuzhen.openeye.databinding.FragmentMineBinding
import com.sanhuzhen.openeye.ui.fragment.mine.FollowFragment
import com.sanhuzhen.openeye.ui.fragment.mine.OpusFragment
import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.sanhuzhen.openeye.R
import com.sanhuzhen.openeye.utils.ToastUtils
import java.io.IOException

class MineFragment : BaseFragment<FragmentMineBinding>() {
    private lateinit var mSharedPreferences: SharedPreferences

    companion object {
        const val REQUEST_CODE = 2
    }

    private lateinit var mAdapter: ViewPaperAdapter
    override fun initBinding(): FragmentMineBinding {
        return FragmentMineBinding.inflate(layoutInflater)
    }

    override fun initView() {
        mSharedPreferences = this.requireActivity().getPreferences(Context.MODE_PRIVATE)
        //TabLayout和ViewPager2的联动
        initTabWithVp()
        //实现用户头像，呢称等的持久化
        initUser()
    }

    private fun initTabWithVp() {
        mAdapter = ViewPaperAdapter(this)
        mAdapter.apply {
            addFragment(OpusFragment(), "作品")
            addFragment(FollowFragment(), "关注")
        }
        binding.vpMine.adapter = mAdapter
        TabLayoutMediator(binding.tlMine, binding.vpMine) { tab, position ->
            tab.text = mAdapter.getFragmentTitle(position)
        }.attach()
    }

    @SuppressLint("IntentReset")
    private fun initUser() {
        // 请求权限
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                1
            )
        }
        loadImage()
        binding.ivHead.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            intent.type = "image/*"
            startActivityForResult(intent, REQUEST_CODE)
        }
        if (mSharedPreferences.getString("输入姓名", "")!!.isNotEmpty()) {
            binding.tvName.text = mSharedPreferences.getString("输入姓名", "")
        }
        binding.tvName.setOnClickListener {
            showAlertDialog(binding.tvName,"输入姓名")
        }
        if (mSharedPreferences.getString("输入签名", "")!!.isNotEmpty()) {
            binding.tvSign.text = mSharedPreferences.getString("输入签名", "")
        }
        binding.tvSign.setOnClickListener {
            showAlertDialog(binding.tvSign,"输入签名")
        }

    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            REQUEST_CODE -> {
                //更新用户头像
                if (resultCode == RESULT_OK && data != null) {
                    val uri = data.data
                    uri?.let {
                        binding.ivHead.setImageURI(it)
                        saveImage(it)
                    }
                }
            }
        }
    }

    private fun saveImage(uri: Uri) {
        try {
            val bitmap = MediaStore.Images.Media.getBitmap(requireContext().contentResolver, uri)
            val fileName = "selected_image.jpg"
            requireActivity().openFileOutput(fileName, Context.MODE_PRIVATE).use {
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, it)
            }
        } catch (e: IOException) {
            e.printStackTrace()
            ToastUtils.ShortToast(requireContext(), "Failed to save image")
        }
    }

    private fun loadImage() {
        val fileName = "selected_image.jpg"
        try {
            val inputStream = requireActivity().openFileInput(fileName)
            if (inputStream != null) {
                val bitmap = BitmapFactory.decodeStream(inputStream)
                binding.ivHead.setImageBitmap(bitmap)
            } else {
                binding.ivHead.setImageResource(R.drawable.img_3)
            }
        } catch (e: IOException) {
            e.printStackTrace()
            Toast.makeText(requireContext(), "Failed to load image", Toast.LENGTH_SHORT).show()
        }
    }

    @SuppressLint("CommitPrefEdits")
    private fun showAlertDialog(view: TextView, title: String) {
        val alertDialog = AlertDialog.Builder(requireContext())
        alertDialog.setTitle(title)
        val input = EditText(requireContext())
        alertDialog.setView(input)
        alertDialog.setPositiveButton("确定") { _, _ ->
            val name = input.text.toString().trim()
            if (name.isNotEmpty()) {
                view.text = name
                mSharedPreferences.edit().putString(title, name).apply()
            }
        }
        alertDialog.setNegativeButton("取消") { _, _ ->
            //取消
        }
        alertDialog.show()
    }
}