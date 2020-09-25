package com.example.retrosnaker

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.os.Parcel
import android.os.Parcelable
import android.util.AttributeSet
import android.view.View
import java.util.jar.Attributes

/**
 *@Description
 *@Author Penny
 *@QQ 1421740716
 */
class MouseLoadingView : View {
    constructor(context: Context):super (context){}
    constructor(context: Context,attrs :AttributeSet?):super (context,attrs){}
    constructor(context: Context,attrs :AttributeSet?,style :Int):super (context,attrs,style){}
    //小圆的半径
    private var BallRadius = 0f
    //嘴的半径
    private var MouseRadius = 0f
    //小圆和嘴的间距
    private var space = 0f
    //嘴的圆心
    private var cx = 0f
    private  var cy = 0f
    //保存动画
    private val animators = mutableListOf<ValueAnimator>()
    //画笔
    private val mPaint = Paint().apply {
        style = Paint.Style.FILL
        color = context.resources.getColor(R.color.aqua,null)
    }
    //角度 --张嘴的动画因子
    private var MouseAngle = 45f

    //小球移动的动画因子
    private var Movecx = 4.5f*BallRadius

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        //计算尺寸
        (Math.min(measuredHeight,measuredWidth)/8.5f).also {r->
            //小球的半径
            BallRadius = r
            //嘴的半径
            MouseRadius = 3f*r
            //间距
            space = r/2f
            //嘴的圆心
            cx = (measuredWidth - 8.5f*r)/2+3f*r
            cy = measuredHeight/2f
        }



    }

    override fun onDraw(canvas: Canvas?) {
        //绘制嘴巴
        canvas?.drawArc(
            cx-MouseRadius,
            cy-MouseRadius,
            cx+MouseRadius,
            cy+MouseRadius,
            MouseAngle,
            360 - 2*MouseAngle,
            true,
            mPaint
        )
        //绘制小球
        canvas?.drawCircle(cx+Movecx,cy,BallRadius,mPaint)
    }

    private fun createAnimator(){
        ValueAnimator.ofFloat(0f,45f,0f).apply {
            duration = 500
            repeatCount = ValueAnimator.INFINITE
            addUpdateListener {
                //改变角度值
                MouseAngle = it.animatedValue as Float
                //刷新界面
                invalidate()
            }
            animators.add(this)
        }
        ValueAnimator.ofFloat(4.5f*BallRadius,0f).apply {
            duration = 500
            repeatCount = ValueAnimator.INFINITE
            addUpdateListener {
                Movecx = it.animatedValue as Float
                invalidate()
            }
            animators.add(this)
        }

    }
    //启动动画
    private fun startAnimators(){
        for (ani in animators){
            ani.start()
        }
    }
    //暂停动画
    private fun stopAnimators(){
        for (ani in animators){
            ani.end()
        }
    }
    //显示动画
    fun showloadingView() {
        createAnimator()
        startAnimators()
    }
    //隐藏动画
    fun hideloadingView(){
        stopAnimators()
    }
}



















