package com.example.androiddemo5

import android.annotation.SuppressLint
import android.graphics.Rect
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.contains
import kotlinx.android.synthetic.main.activity_main.*

/**

1./**
//lambda表达式
mTextView.setOnClickListener{v: View? ->
v?.background = getDrawable(R.color.colorAccent)
}
 */

2./** 方法中只有一个参数 可以省略
mTextView.setOnClickListener{
it?.background = getDrawable(R.color.colorAccent) }
 */

3./** 匿名对象
mTextView.setOnClickListener(object :View.OnClickListener{
@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
override fun onClick(p0: View?) {
p0?.background = getDrawable(R.color.colorAccent)
}

})
 */

4./** 其他类监听
mTextView.setOnClickListener(MyListener())
inner class MyListener : View.OnClickListener{
@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
override fun onClick(p0: View?) {
p0?.background = getDrawable(R.color.colorAccent)
}
 */

5./** 自己监听
mTextView.setOnClickListener(this)
@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
override fun onClick(p0: View?) {
p0?.background = getDrawable(R.color.colorAccent)
}
 */

*/

class MainActivity : AppCompatActivity() {
    private var barHeight = 0

    //获取顶部高度barHeight
    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        //获取屏幕的尺寸
        val Screenrect = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(Screenrect)

        //获取绘制区域的尺寸
        val drawRect = Rect()
        //通过获取window上的content容器 ->容器的rect
        window.findViewById<ViewGroup>(Window.ID_ANDROID_CONTENT).getDrawingRect(drawRect)

        //获取顶部高度 = 屏幕尺寸 - 绘制区域尺寸
        barHeight = Screenrect.heightPixels - drawRect.height() + mconstraintLayout.top
        Log.v("pxd", "顶部高度是$barHeight")
    }

    //保存所有线的tag值
    private val linesTag :Array<Int> by lazy {
        return@lazy arrayOf(
            12,23,45,56,78,89, //横线
            14,25,36,47,58,69, //竖线
            15,26,24,35,48,59,57,68 //斜线
        )
    }

    //记录上一个点亮的点
    private var lastHaighlightdot : ImageView ?= null

    //记录原始密码
    private var orgPassword :String? = null
    //记录第一次设置的密码
    private var firPassword :String? =null

    //记录密码
    private val password = StringBuilder()

    //用数组保存圆点
    private val dots :Array<ImageView> by lazy {
        return@lazy arrayOf(sdot1,sdot2,sdot3,sdot4,sdot5
        ,sdot6,sdot7,sdot8,sdot9)
    }

    //保存线
    private val lines :Array<ImageView> by lazy {
        return@lazy arrayOf(line12,line14,line23,line25,line36,line45,line47
        ,line56,line58,line69,line78,line89,line15,line26,line24,line35,line48,line59,
        line57,line68)
    }

    //获取圆点的位置
    private fun getRect(v:View) = Rect(v.left,v.top,v.right,v.bottom)

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        SharedPreference.getInstance(this).getPassword().also {
            if (it ==null){
                mPoints.text = "请设置您的密码图案："
            }else{
                mPoints.text = "请绘制您的密码图案："
                orgPassword = it
            }
        }
    }

    @SuppressLint("ResourceType")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
            if (!((event?.x?.toInt()!! >=0&&event.x.toInt()<mconstraintLayout.width)&&
                ((event.y - barHeight).toInt()>=0&&(event.y - barHeight).toInt()<mconstraintLayout.height)))
            {
                return true
            }
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                ifDotscontainevent(event)
            }
            MotionEvent.ACTION_MOVE -> {
                ifDotscontainevent(event)
            }
            MotionEvent.ACTION_UP -> {
                //判断是不是首次设置密码
                if (orgPassword == null) {
                    if (firPassword == null) {
                        firPassword = password.toString()
                        mPoints.text = " 请确认密码："
                    } else {
                        //确认密码
                        if (firPassword == password.toString()) {
                            mPoints.text = "设置密码成功"
                            SharedPreference.getInstance(this).savePassword(firPassword).also {
                                orgPassword = it.toString()
                            }
                        } else {
                            mPoints.text = "两次密码不一致，请重新绘制"
                            firPassword = null
                        }
                    }
                } else {
                    if (firPassword == password.toString()) {
                        mPoints.text = "解锁成功！"
                        SharedPreference.getInstance(this).savePassword(firPassword!!)
                    } else {
                        mPoints.text = "密码错误，请重新绘制"
                    }
                }
                turnback(dots, lines)
                lastHaighlightdot = null

            }
        }



        return true
    }


    //判断点击位置是否在圆点内,如果在就点亮,并记录tag值
    private fun ifDotscontainevent(event: MotionEvent){
        for (item in dots){
            if (getRect(item).contains(event.x.toInt(), (event.y - barHeight).toInt())
                &&item.visibility == View.INVISIBLE){
                if (lastHaighlightdot == null){
                    //是第一个点
                    highlightDot(item)
                }else{
                    //不是第一个点，滑动的时候已经点亮过其他的点
                    val priTag =( lastHaighlightdot?.tag as String ).toInt()
                    val curTag = (item.tag as String).toInt()
                    val lineTag = if (priTag>curTag)(curTag*10+priTag) else (priTag*10+curTag)
                    //判断是否有这条线
                    if (linesTag.contains(lineTag)){
                        //点亮这个点
                        highlightDot(item)
                        //点亮线
                        mconstraintLayout.findViewWithTag<ImageView>(lineTag.toString()).apply {
                            visibility = View.VISIBLE
                        }
                    }


                }
            }
        }
    }

    //显示连线
    private fun highlightDot(d:ImageView){
        //点亮这个点
        d.visibility = View.VISIBLE
        password.append(d.tag)
        lastHaighlightdot =d
    }

    //手指离开屏幕的时候，圆点不点亮
    private  fun turnback(d1:Array<ImageView>,d2:Array<ImageView>){
        for (item in d1){
            if (item.visibility == View.VISIBLE){
                item.visibility = View.INVISIBLE
            }
        }
        for (item in d2){
            if (item.visibility ==View.VISIBLE)
                item.visibility =View.INVISIBLE
        }
        password.clear()
    }


}


/** //监听点击事件
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when(event?.action){
            MotionEvent.ACTION_DOWN ->{
                Log.v("pxd","手指点击")
               Judge2(event)
            }
            MotionEvent.ACTION_MOVE ->{
                Log.v("pxd","手指滑动")
                Judge2(event)
            }
            MotionEvent.ACTION_UP ->{
                Log.v("pxd","离开屏幕")
            }else -> {
            Log.v("pxd","被其它应用打断")
        }
        }
        return super.onTouchEvent(event)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    //判断点是否在容器内 Method 1:
    private fun Judge(event: MotionEvent){
        val rect :Rect = Rect()
        mTextView.requestRectangleOnScreen(rect)
        rect.right = rect.left+mTextView.width
        rect.bottom = rect.top+mTextView.height
        rect.contains(event.x.toInt(),event.y.toInt()).also {
            if (it){
                mTextView.background = getDrawable(R.color.colorPrimaryDark)
            }else{
                mTextView.background = getDrawable(R.color.colorAccent)
            }
        }
    }

    //判断点是否在容器内 Method 2:
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun Judge2(event: MotionEvent){
        val viewRect = Rect(mTextView.left,mTextView.top,mTextView.right,mTextView.bottom)
        //判断点击位置是否在目标范围内
        viewRect.contains(event.x.toInt(),(event.y - barHeight).toInt()).also {
            if (it){
                mTextView.background = getDrawable(R.color.colorPrimaryDark)
            }else{
                mTextView.background = getDrawable(R.color.colorAccent)
            }
        }
    }
    */





