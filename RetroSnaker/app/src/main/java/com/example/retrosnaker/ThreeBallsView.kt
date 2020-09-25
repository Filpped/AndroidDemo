package com.example.retrosnaker

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

/**
 *@Description
 *@Author Penny
 *@QQ 1421740716
 */
class ThreeBallsView : View {
    constructor(context: Context):super (context){}
    constructor(context: Context, attrs : AttributeSet?):super (context,attrs){}
    constructor(context: Context, attrs : AttributeSet?, style :Int):super (context,attrs,style){}

    //小球的半径
    private var BallRadius = 0f
    //小球的间距
    private var space = 0f
    //小球1的圆心
    private var cx = 0f
    private var cy = 0f

    //缩放的比例
    private var mscale = arrayOf(1f,1f,1f)

    //延时时间
    private var delays = arrayOf(0L,120L,240L)

    //动画对象
    private var animators = mutableListOf<ValueAnimator>()
    //画笔
    private val mPaint = Paint().apply {
        style = Paint.Style.FILL
        color = context.resources.getColor(R.color.aqua,null)
    }
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        BallRadius = Math.min(measuredHeight,measuredWidth)/7f
        space = BallRadius/2f
        cx = (measuredWidth - 7f*BallRadius)/2 + BallRadius
        cy = measuredHeight/2f
    }

    override fun onDraw(canvas: Canvas?) {
      /*  //第一种方法，绘制三个圆
        canvas?.drawCircle(cx,cy,BallRadius,mPaint)
        canvas?.drawCircle(cx+2.5f*BallRadius,cy,BallRadius,mPaint)
        canvas?.drawCircle(cx+5f*BallRadius,cy,BallRadius,mPaint)

       */
        // 第二种方法 移动画布
        for (i in 0..2){
            canvas?.save()
            canvas?.translate(cx+i*2.5f*BallRadius,cy)
            canvas?.scale(mscale[i],mscale[i])
            canvas?.drawCircle(0f,0f,BallRadius,mPaint)
            canvas?.restore()
        }
    }

    private fun createAnimator(){
        for (i in 0..2){ValueAnimator.ofFloat(1f,0.4f).apply {
            duration = 650
            startDelay = delays[i]
            repeatCount = ValueAnimator.INFINITE
            addUpdateListener {
                mscale[i] = it.animatedValue as Float
                invalidate()
            }
            animators.add(this)

        }


        }
    }

    //开始动画
    fun show(){
        createAnimator()
        for (item in animators)
            item.start()
    }
    //结束动画
    fun hide(){
        for (item in animators)
            item.end()

    }
}