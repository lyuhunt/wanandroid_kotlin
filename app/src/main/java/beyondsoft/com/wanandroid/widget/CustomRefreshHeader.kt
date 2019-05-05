package beyondsoft.com.wanandroid.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.zhouyou.recyclerview.refresh.BaseRefreshHeader
import com.zhouyou.recyclerview.refresh.IRefreshHeader

/**
 * 自定义有动画效果的下拉刷新
 */
class CustomRefreshHeader: BaseRefreshHeader {

    private val TAG = "CustomRefreshHeader"
    private var mContext : Context ?= null
    private var mCustomAnimView : CustomRefreshView ?= null
    private var mState : Int = IRefreshHeader.STATE_NORMAL

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attributes: AttributeSet?) : super(context, attributes) {
        mContext = context
    }

    override fun getView(): View {
        mCustomAnimView = CustomRefreshView(context)
        return mCustomAnimView!!
    }

    override fun setState(state: Int) {
        super.setState(state)
        if (mState == state) return
        //选择自定义需要处理的状态：STATE_NORMAL、STATE_RELEASE_TO_REFRESH、STATE_REFRESHING、STATE_DONE
        //以下是我自定义动画需要用到的状态判断，你可以根据自己需求选择。
        when(state) {
            IRefreshHeader.STATE_NORMAL -> { //下拉状态
                mCustomAnimView!!.mImageView!!.visibility = View.GONE
                mCustomAnimView!!.mShowImage!!.visibility = View.VISIBLE
            }
            IRefreshHeader.STATE_RELEASE_TO_REFRESH -> { //下拉达到刷新临界点的状态
            }
            IRefreshHeader.STATE_REFRESHING -> { //刷新中
                mCustomAnimView!!.mImageView!!.visibility = View.VISIBLE
                mCustomAnimView!!.mShowImage!!.visibility = View.GONE
                mCustomAnimView!!.startAnimal()
            }
            IRefreshHeader.STATE_DONE -> { //刷新结束
                mCustomAnimView!!.stopAnimal()
            }
        }
        mState = state
    }
}