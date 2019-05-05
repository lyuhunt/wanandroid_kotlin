package beyondsoft.com.wanandroid.widget

import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import com.zhouyou.recyclerviewsdk.R

class CustomRefreshView(context: Context, attributeSet: AttributeSet? = null, defStyleAttr: Int = -1)
    : LinearLayout(context, attributeSet, defStyleAttr) {

    var mImageView: ImageView? = null
    var mShowImage : ImageView? = null
    private var mAnimationDrawable: AnimationDrawable? = null

    init {
        //header的padding必须在BaseRefreshHeader中通过java代码设置 在布局文件中直接设置会有bug
        val header = LayoutInflater.from(context).inflate(R.layout.clife_loading_header2, this)
        mImageView = header.findViewById<View>(R.id.pull_to_refresh_image) as ImageView
        mShowImage = header.findViewById<View>(R.id.iv_refresh_image) as ImageView
        mImageView!!.setImageResource(R.drawable.clife_refresh_loading)
        mAnimationDrawable = mImageView!!.drawable as AnimationDrawable
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        stopAnimal()
    }

    fun startAnimal() {
        if (mAnimationDrawable != null && !mAnimationDrawable!!.isRunning) {
            mAnimationDrawable!!.start()
        }
    }

    fun stopAnimal() {
        if (mAnimationDrawable != null && mAnimationDrawable!!.isRunning) {
            mAnimationDrawable!!.stop()
        }
    }
}