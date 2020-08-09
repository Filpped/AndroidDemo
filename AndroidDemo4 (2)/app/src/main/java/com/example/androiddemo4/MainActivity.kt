package com.example.androiddemo4

import android.app.Activity
import android.content.Intent
import android.media.tv.TvView
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.InputEvent
import android.view.KeyEvent
import android.widget.EditText
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.widget.addTextChangedListener
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
/**            只能传递系统默认的基本类型
                intent.putExtra("num1",mnum1.text)
                intent.putExtra("num2",mnum2.text)
               mres.setText(intent.data?.schemeSpecificPart)
*/
class MainActivity : Activity() {

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //回调旋转前的数据
        savedInstanceState?.getString("text")?.also {
            mres.text = it
        }
        var num1 = 0
        var num2 = 0
        val editText1 = findViewById<EditText>(R.id.mnum1)
        //设置num1的退格监听
        editText1.addOnUnhandledKeyEventListener(@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        object :TvView.OnUnhandledInputEventListener, View.OnUnhandledKeyEventListener {
            override fun onUnhandledKeyEvent(v: View?, event: KeyEvent?): Boolean {

                if (event != null) {
                    if (event.action == KeyEvent.KEYCODE_DEL) {
                        if (editText1.text.isNotEmpty()) {
                            if (editText1.text.length - 1 == 0) {
                                editText1.setText("")
                                editText1.setSelection(0)
                            } else {
                                val newText = editText1.text.substring(0, editText1.text.length - 1)
                                editText1.setText(newText)
                                editText1.setSelection(newText.length)
                            }
                        }
                    }
                }
                        return false
            }

            override fun onUnhandledInputEvent(event: InputEvent?): Boolean {
                TODO("Not yet implemented")
            }

        })
        //设置num1中数据变化的监听
        editText1.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                //s  输入结束呈现在输入框中的信息
                if (s.toString().isEmpty()){
                    mnum1.setText("0")
                }else{num1 = s.toString().toInt()}

            }
            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
                //text  输入框中改变前的字符串信息
                //start 输入框中改变前的字符串的起始位置
                //count 输入框中改变前后的字符串改变数量一般为0
                //after 输入框中改变后的字符串与起始位置的偏移量
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                //text  输入框中改变后的字符串信息
                //start 输入框中改变后的字符串的起始位置
                //before 输入框中改变前的字符串的位置 默认为0
                //count 输入框中改变后的一共输入字符串的数量
            }
        })

        val editText2 = findViewById<EditText>(R.id.mnum2)
        editText2.addOnUnhandledKeyEventListener(@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        object :TvView.OnUnhandledInputEventListener, View.OnUnhandledKeyEventListener {
            override fun onUnhandledKeyEvent(v: View?, event: KeyEvent?): Boolean {

                if (event != null) {
                    if (event.action == KeyEvent.KEYCODE_DEL) {
                        if (editText2.text.isNotEmpty()) {
                            if (editText2.text.length - 1 == 0) {
                                editText2.setText("")
                                editText2.setSelection(0)
                            } else {
                                val newText = editText2.text.substring(0, editText2.text.length - 1)
                                editText2.setText(newText)
                                editText2.setSelection(newText.length)
                            }
                        }
                    }
                }
                return false
            }
            
            override fun onUnhandledInputEvent(event: InputEvent?): Boolean {
                TODO("Not yet implemented")
            }

        })
        editText2.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s.toString().isEmpty()){
                    mnum2.setText("0")
                }else{num2 = s.toString().toInt()}
            }

            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Log.v("pxd", "输入了数据")
            }
        })
        //“直接计算”按钮监听事件
        mjs.setOnClickListener {
            Log.v("pxd", "$num1 + $num2")
            val intent = Intent()
            val bundles = Bundle()
            bundles.putInt("num1", num1)
            bundles.putInt("num2", num2)
            intent.putExtras(bundles)
            intent.setClass(this, CalculationMethod::class.java)
            startActivityForResult(intent, 1)

        }

        //“分享”按钮的监听事件
        mfx.setOnClickListener {
           Intent().apply{
               action = "android.intent.action.CALL"
               putExtra("res",mres.text.toString())
           }.also {
               startActivityForResult(it,2)
           }


        }
        }

    //保存旋转界面前的数据
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if (mres.text.toString()!="0") {
            outState.putString("text", mres.text.toString())
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 1) {
            when (resultCode) {
                0 -> data?.getStringExtra("res").also { mres.text = it }
                2 -> data?.getStringExtra("res1").also { mres.text = it }
            }
        }else if(requestCode ==2){
            if(resultCode ==1){
                data?.getStringExtra("res2").also {
                    Toast.makeText(this, it, Toast.LENGTH_LONG).show()
                }
            }
        }
    }
    }




