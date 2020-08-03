package com.example.androiddemo4

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_calculation_method.*
import kotlinx.android.synthetic.main.activity_main.*

class CalculationMethod : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculation_method)
        var bundle = Bundle()
        bundle = this.intent.getExtras()!!
        val n1: Int = bundle.getInt("num1")
        val n2 :Int = bundle.getInt("num2")
        val res :Int = n1+n2
        Log.v("pxd", res.toString())
        mbtn1.setOnClickListener {
            Intent().putExtra("res","$res").also { setResult(0,it) }
            finish()
        }


        mbtn2.setOnClickListener {
            startActivity( Intent().apply {
                //打电话页面
                   action = Intent.ACTION_DIAL
                   data = Uri.parse("tel:15083418913")
                //发信息页面
//                action = Intent.ACTION_SENDTO
//                data = Uri.parse("sms:123456")
                //相机页面
//                action = "android.media.action.IMAGE_CAPTURE"
            })
            Intent().putExtra("res1","$res").also { setResult(2,it) }
            finish()
        }
    }
}