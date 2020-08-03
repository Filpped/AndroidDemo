package com.example.androiddemo1

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_wechat.*

class Wechat : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wechat)
        val res = intent.getStringExtra("res")
        Log.v("pxd","$res")
        textView3.text = "欢迎来到分享APP，已经接收到了你的分享：这是我的第一个计算器，" +
                "计算结果是 $res"
        mbtn.setOnClickListener {
            startActivity(Intent().apply {
                action = "android.intent.action.ANSWER"
            })
        }
    }
}