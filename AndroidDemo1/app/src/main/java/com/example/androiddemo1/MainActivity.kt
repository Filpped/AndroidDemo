package com.example.androiddemo1

import android.animation.Animator
import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        mloginBtn.setOnClickListener { v:View ->
//            println("3.按钮被点击了")
//        }

/**        mloginBtn.setOnClickListener(this)
            override fun onClick(v: View?) {
        println("1.按钮被点击了")
    }
 */

/**
        mloginBtn.run {
                mloginBtn.setOnClickListener(object : View.OnClickListener{
                    override fun onClick(v: View?) {
                        println("2.按钮被点击了")
                    }
                })
        }
*/

        mloginBtn.setOnClickListener{
            ObjectAnimator.ofFloat(mImage,"rotation",0f,360f).apply {
                duration = 100
                repeatCount = 6
                start()
                addListener(object : MyAnimatorListener() {
                    override fun onAnimationEnd(animation: Animator?) {
                        super.onAnimationEnd(animation)
                        //界面跳转
                        startActivity(Intent(this@MainActivity,DetailActivity::class.java))
                    }
                })
            }


/**
        ObjectAnimator.ofFloat(mImage,"rotation",0f,360f).also {
            it.duration = 100
            it.repeatCount = 3
            it.addListener(object : Animator.AnimatorListener{
            override fun onAnimationRepeat(animation: Animator?) {}
            override fun onAnimationEnd(animation: Animator?) {
            //界面跳转
                startActivity(Intent(this@MainActivity,DetailActivity::class.java))
            }
            override fun onAnimationCancel(animation: Animator?) {}
            override fun onAnimationStart(animation: Animator?) {}
            })
        }
*/


/**            动画 ： 旋转 平移 缩放 透明度
            val ani = ObjectAnimator.ofFloat(mImage,"rotation",0f,360f)
            ani.duration = 100
            ani.repeatCount = 3
            ani.start()
            ani.addListener(object : Animator.AnimatorListener{
                override fun onAnimationRepeat(animation: Animator?) {
                }

                override fun onAnimationEnd(animation: Animator?) {
                    //界面跳转
                    startActivity(Intent(this@MainActivity,DetailActivity::class.java))
               }

                override fun onAnimationCancel(animation: Animator?) {
                }

                override fun onAnimationStart(animation: Animator?) {
                }

           })

*/
        }
    }

}