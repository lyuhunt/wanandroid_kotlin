package beyondsoft.com.wanandroid.base

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import beyondsoft.com.wanandroid.R
import beyondsoft.com.wanandroid.utils.LogUtils

/**
 * MVP的基类 包含了四种不同状态 正常布局 加载失败布局 空布局 加载中布局
 *
 *      继承BaseMvpFragment的Fragment如果需要展示这四种布局
 *      就必须定义必须为正常布局定义id为normal_view
 *      且正常布局必须有ViewGroup的父布局
 */
abstract class BaseMvpFragment<V : IView, P : IPresenter<V>> : BaseFragment(), IView {

    private val TAG = "BaseMvpFragment"

    private val NORMAL_STATE = 0
    private val LOADING_STATE = 1
    private val ERROR_STATE = 2
    private val EMPTY_STATE = 3
    private var currentState = NORMAL_STATE

    private var mErrorView: View? = null
    private var mLoadingView: View? = null
    private var mEmptyView: View? = null
    private var mNormalView: View? = null

    var mPresenter : P? = null


    override fun initView() {
        LogUtils.e(TAG, "initView")
        mNormalView = view!!.findViewById(R.id.normal_view)
        if (mNormalView != null) {
            if (mNormalView?.parent !is ViewGroup) {
                throw IllegalStateException("The parent layout of mNormalView must belong to the viewgroup")
            }
            val parent = mNormalView?.parent as ViewGroup
            mEmptyView = View.inflate(mContext, R.layout.view_empty, null)
            mErrorView = View.inflate(mContext, R.layout.view_error, null)
            mLoadingView = View.inflate(mContext, R.layout.view_loading, null)
            parent.addView(mEmptyView)
            parent.addView(mErrorView)
            parent.addView(mLoadingView)
            // 重新加载
            mErrorView?.findViewById<TextView>(R.id.tv_reload)?.setOnClickListener {
                reload()
            }
            mEmptyView?.visibility = View.GONE
            mErrorView?.visibility = View.GONE
            mLoadingView?.visibility = View.GONE
        }

        mPresenter = createPresenter()
        mPresenter?.attachView(this as V)
    }

    abstract fun createPresenter(): P

    override fun showNormal() {
        hideCurrentView()
        currentState = NORMAL_STATE
        mNormalView?.visibility = View.VISIBLE
    }

    override fun showLoading() {
        hideCurrentView()
        currentState = LOADING_STATE
        mLoadingView?.visibility = View.VISIBLE
    }

    override fun showEmpty() {
        hideCurrentView()
        currentState = EMPTY_STATE
        mEmptyView?.visibility = View.VISIBLE
    }

    override fun showError(err: String) {
        Toast.makeText(mActivity, err, Toast.LENGTH_SHORT).show()
        hideCurrentView()
        currentState = ERROR_STATE
        mErrorView?.visibility = View.VISIBLE
    }

    private fun hideCurrentView() {
        when (currentState) {
            NORMAL_STATE -> mNormalView?.visibility = View.GONE
            ERROR_STATE -> mErrorView?.visibility = View.GONE
            EMPTY_STATE -> mEmptyView?.visibility = View.GONE
            LOADING_STATE -> mLoadingView?.visibility = View.GONE
            else -> {
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mPresenter?.detachView()
        mPresenter = null
    }

    override fun reload() {
    }

    fun hideAll() {
        mEmptyView?.visibility = View.GONE
        mErrorView?.visibility = View.GONE
        mLoadingView?.visibility = View.GONE
    }
}
