package com.sanhuzhen.openeye.utils

import android.content.Context
import android.widget.Toast

object ToastUtils{
    fun LongToast(context: Context, msg: String){
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
    }
    fun ShortToast(context: Context, msg: String){
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }
}