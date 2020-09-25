package com.example.retrosnaker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mStartbtn.setOnClickListener{
            mloadingView.showloadingView()
            mThreeballs.show()
        }

        mStopbtn.setOnClickListener{
            mloadingView.hideloadingView()
            mThreeballs.hide()
        }
    }
}