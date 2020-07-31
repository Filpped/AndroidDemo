package com.example.androiddemo3

import android.annotation.SuppressLint
import android.app.TaskStackBuilder
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.marginLeft
import androidx.core.view.marginTop
import androidx.core.view.size

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        使用线性布局
//        addLinearlayout()
//        使用相对布局
//        addRelativelayout()
        addConstraintlayout()
    }

    private fun addConstraintlayout(){
         ConstraintLayout(this).apply {
             id = R.id.mcontainer
            layoutParams = ConstraintLayout.LayoutParams(
                    ConstraintLayout.LayoutParams.MATCH_PARENT,
                    ConstraintLayout.LayoutParams.MATCH_PARENT
            )
            background = getDrawable(R.color.colorPrimaryDark)
            setContentView(this)
        }.apply {
             ImageView(this@MainActivity).apply {
                 id = R.id.mimage
                 layoutParams = ConstraintLayout.LayoutParams(
                         dp(180),dp(100)
                 ).apply {
                     leftToLeft = R.id.mcontainer
                     topToTop = R.id.mcontainer
                     setMargins(dp(5),dp(5),0,0)
                 }
                 setImageResource(R.mipmap.image)
                 scaleType = ImageView.ScaleType.CENTER_CROP
                 addView(this)
             TextView(this@MainActivity).apply {
                 id = R.id.mtitle
                 layoutParams = ConstraintLayout.LayoutParams(
                         dp(210),dp(100)
                 ).apply {
                     topToTop = R.id.mimage
                     bottomToBottom = R.id.mimage
                     leftToLeft = R.id.mimage
                     marginStart = dp(185)
                     marginEnd = dp(5)
                 }
                 text = "字节跳动被曝将推动国内业务上市，回应称对市场传言不予置评。"
                 setTextColor(getColor(R.color.colorWhite))
                 textSize = 20f
                 addView(this)
             }
             TextView(this@MainActivity).apply {
                 layoutParams = ConstraintLayout.LayoutParams(
                         ConstraintLayout.LayoutParams.MATCH_CONSTRAINT,
                         ConstraintLayout.LayoutParams.MATCH_CONSTRAINT
                 ).apply {
                     topToBottom = R.id.mimage
                     leftToLeft = R.id.mimage
                     rightToRight = R.id.mtitle
                     bottomToBottom = R.id.mcontainer

                     setMargins(dp(5),dp(15),dp(5),0)
                 }
                 text = "       7月31日，据路透社报道，抖音、今日头条母公司字节跳动正考虑推动国内业务上市，" +
                         "上市地点或在香港或上海，公司相对倾向于香港。"+"\n"+"        字节跳动还同时在研究将" +
                         "海外业务在欧洲或美国上市。字节跳动的海外业务包括TikTok。"+"\n"+"       对此，字节跳动回应" +
                         "澎湃新闻记者称，对市场传言，不予置评。"+"\n"+"       2018年7月，华尔街日报援引知情人士的话报道称，字节跳动正在与投行磋商，" +
                         "考虑以450亿美元的估值在香港IPO（首次公开募股），计划在年内进行。当时字节跳动回应称，公司目前并没有" +
                         "上市的计划和安排。"+"\n"+"     2019年10月，英国金融时报报道，字节跳动（ByteDance）考虑最快在2020年一季度于香港上市。当时字节跳动方面回应称，报道有误。"
                 setTextColor(getColor(R.color.colorWhite))
                 textSize = 19f
                 addView(this)
             }
        }

         }

    }

    private fun addRelativelayout(){
        var container = RelativeLayout(this).apply {
            id = R.id.mcontainer
            layoutParams = RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.MATCH_PARENT
            )
            background = getDrawable(R.color.colorPrimaryDark)
            setContentView(this)
        }.apply {
            ImageView(this@MainActivity).apply {
                id = R.id.mimage
                layoutParams = RelativeLayout.LayoutParams(
                        dp(180),dp(100)
                ).apply {
                    setMargins(dp(5),dp(10),0,0)
                }
                setImageResource(R.mipmap.image)
                scaleType = ImageView.ScaleType.CENTER_CROP
                addView(this)
            }
            TextView(this@MainActivity).apply {
                id = R.id.mtitle
                layoutParams = RelativeLayout.LayoutParams(
                        dp(200),dp(130)
                ).apply {
//                    setMargins(dp(210),dp(20),dp(15),dp(10))
                    //和图片的右边对齐
                    addRule(RelativeLayout.RIGHT_OF,R.id.mimage)
                    //和容器左边对齐
                    addRule(RelativeLayout.ALIGN_PARENT_END,R.id.mcontainer)
                    //和图片顶部对齐
                    addRule(RelativeLayout.ALIGN_TOP,R.id.mimage)
                    //和图片底部对齐
                    addRule(RelativeLayout.ALIGN_BOTTOM,R.id.mimage)
                    //左边离图片的距离
                    marginStart = dp(10)
                }
                text = "法院判决抖音、微信读书侵害用户个人信息，抖音腾讯都回应了！"
                setTextColor(getColor(R.color.colorWhite))
                textSize = 19f
                addView(this)
            }
            TextView(this@MainActivity).apply {
                layoutParams = RelativeLayout.LayoutParams(
                        dp(380),dp(400)
                ).apply {
                    setMargins(dp(10),dp(145),dp(10),dp(0))
                    //addRule(RelativeLayout.ALIGN_PARENT_BOTTOM,R.id.mcontainer)
                }
                text = "        用户起诉抖音和微信读书侵犯个人隐私案件有了新进展。据央视新闻报道，7月30日，北京互联网法院作出一审宣判，" +
                        "认定抖音、微信读书均有侵害用户个人信息的情形。两案也成为《中华人民共和国民法典》颁布后，" +
                        "体现民法典保护互联网时代公民个人信息权益的典型案件。"+"\n"+"        随后，腾讯回应称将尊重法院判决，" +
                        "之前已对相关功能进行了优化，对相关社交功能进行了强提示。抖音则表示，对于法院一审判决会提起上诉。"
                setTextColor(getColor(R.color.colorWhite))
                textSize = 22f
                addView(this)
            }
        }
    }

    @SuppressLint("ResourceAsColor")
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun addLinearlayout(){
        var container = LinearLayout(this).apply {
            //设置大小
            layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT)
            //设置背景颜色
            background = getDrawable(R.color.colorPrimary)
            //设置方向
            orientation = LinearLayout.VERTICAL
        }.also { setContentView(it) }

        LinearLayout(this).apply {
            //设置大小
            layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    dp(120))
            //设置背景颜色
            background = getDrawable(R.color.colorPrimaryDark)
            //设置方向
            orientation = LinearLayout.HORIZONTAL
            container.addView(this)
        }.apply {
            ImageView(this@MainActivity).apply {
                layoutParams = LinearLayout.LayoutParams(
                        dp(120),
                        LinearLayout.LayoutParams.MATCH_PARENT
                )
                setImageResource(R.mipmap.image)
                scaleType = ImageView.ScaleType.CENTER_CROP
                addView(this)
            }
            TextView(this@MainActivity).apply {
                layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply {
                    marginStart = dp(25)
                    setMargins(dp(25),dp(30),dp(10),dp(15))
                }
                text = "北斗正式开通   开启服务人类的新篇章"
                setTextColor(getColor(R.color.colorWhite))
                textSize = 25f
                addView(this)
            }
        }

        TextView(this).apply {
            layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT
            ).apply {
                setMargins(dp(10),dp(20),dp(10),dp(10))
            }
            //background = getDrawable(R.color.colorAccent)
            setTextColor(getColor(R.color.colorWhite))
            textSize = 25f
            text = "        2020年7月31日北斗正式开通，北斗三号全球导航卫星系统竣工通车仪式在人民大会堂隆重举行。" +
                    "中国向世界庄严宣告，中国自主建设和运营的全球卫星导航系统全面建成，中国北斗自信开启了优质服务世界、造福人类的新篇章!"+
                    "\n"+"\n"+"        北斗系统是党中央决定实施的国家重大科技工程。它是一个规模最大、覆盖范围最广、服务性能最高、与人们生活关系最密切的庞大复杂空间系统。"
            container.addView(this)

        }
    }

    private fun dp(dp:Int): Int {
        return (resources.displayMetrics.density * dp).toInt()
    }
}