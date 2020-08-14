package com.example.androiddemo5

import android.annotation.SuppressLint
import android.content.Context

/**
 *@Description
 *@Author Penny
 *@QQ 1421740716
 */
class SharedPreference private constructor(){
    private  val File_Name :String = "My_File"
    private  val Key = "My_FileKey"

    companion  object {
        private var instance: SharedPreference? = null
        private var mContext: Context? = null
        fun getInstance(context: Context): SharedPreference {
            mContext = context
            if (instance == null) {
                synchronized(this) {
                    instance = SharedPreference()
                }
            }
            return instance!!
        }
    }
        @SuppressLint("CommitPrefEdits")
        fun savePassword(text: String?){
            //获取并使用preference对象
            mContext?.getSharedPreferences(File_Name,Context.MODE_PRIVATE).also {
                val edit = it?.edit()
                edit?.putString(Key,text)
                edit?.apply {}
            }
        }
        fun getPassword() : String? {
            //获取preference对象
            val sharedPreference = mContext?.getSharedPreferences(File_Name,Context.MODE_PRIVATE)
            return sharedPreference?.getString(Key,null)
        }

}