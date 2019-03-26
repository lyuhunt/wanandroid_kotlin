package beyondsoft.com.wanandroid.ui.fragment

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import beyondsoft.com.wanandroid.R
import beyondsoft.com.wanandroid.base.BaseMvpFragment
import beyondsoft.com.wanandroid.log.LogUtilHelper
import beyondsoft.com.wanandroid.mvp.contract.NavigationContract
import beyondsoft.com.wanandroid.mvp.model.bean.NavigationBean
import beyondsoft.com.wanandroid.mvp.presenter.NavigationPresenter
import beyondsoft.com.wanandroid.ui.adapter.NavigationAdapter
import beyondsoft.com.wanandroid.ui.adapter.NavigationTabAdapter
import beyondsoft.com.wanandroid.utils.LogUtils
import com.zhouyou.recyclerview.divider.HorizontalDividerItemDecoration
import kotlinx.android.synthetic.main.fragment_navigation.*
import q.rorbin.verticaltablayout.VerticalTabLayout
import q.rorbin.verticaltablayout.widget.TabView

class NavigationFragment : BaseMvpFragment<NavigationContract.View, NavigationContract.Presenter>(), NavigationContract.View {

    private val TAG = "NavigationFragment"
    private val mData = mutableListOf<NavigationBean>()

    private var bScroll: Boolean = false
    private var currentIndex: Int = 0
    private var bClickTab: Boolean = false

    private val mLinearLayoutManager : LinearLayoutManager by lazy {
        LinearLayoutManager(context)
    }
    private val mAdapter : NavigationAdapter by lazy {
        NavigationAdapter(context!!)
    }

    override fun createPresenter(): NavigationContract.Presenter = NavigationPresenter()
    override fun getLayoutRes(): Int = R.layout.fragment_navigation

    override fun getData() {
        mPresenter?.getNavigation()
    }

    override fun initData() {
    }

    override fun initView() {
        super.initView()
        showLoading()
        recyclerView.run {
            adapter = mAdapter
            layoutManager = mLinearLayoutManager
            addItemDecoration(HorizontalDividerItemDecoration.Builder(context)
                    .drawable(R.drawable.shape_cursor)
                    .size(10)
                    .build())
            setHasFixedSize(true)
        }
        leftRightLink()
    }

    private fun leftRightLink() {
        // 左边随右边联动
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (bScroll) {
                    scrollRecyclerView()
                }
            }
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (bScroll && (newState == RecyclerView.SCROLL_STATE_IDLE)) {
                    scrollRecyclerView()
                }
                rightLinkLeft(newState)
            }
        })
        // 右边随左边联动
        navigation_tab_layout.addOnTabSelectedListener(object : VerticalTabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabView?, position: Int) {
            }
            override fun onTabSelected(tab: TabView?, position: Int) {
                bClickTab = true
                selectTab(position)
            }
        })
    }

    private fun scrollRecyclerView() {
        bScroll = false
        val indexDistance: Int = currentIndex - mLinearLayoutManager.findFirstVisibleItemPosition()
        if (indexDistance > 0 && indexDistance < recyclerView!!.childCount) {
            val top: Int = recyclerView.getChildAt(indexDistance).top
            recyclerView.smoothScrollBy(0, top)
        }
    }

    private fun rightLinkLeft(newState: Int) {
        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
            if (bClickTab) {
                bClickTab = false
                return
            }
            val firstPosition: Int = mLinearLayoutManager.findFirstVisibleItemPosition()
            LogUtils.e(TAG, "firstPosition = $firstPosition")
            if (firstPosition != currentIndex) {
                currentIndex = firstPosition
                LogUtils.e(TAG, "currentIndex = $currentIndex")
                setChecked(currentIndex)
            }
        }
    }

    private fun setChecked(position: Int) {
        if (bClickTab) {
            bClickTab = false
        } else {
            LogUtils.e(TAG, "currentIndex2 = $currentIndex")
            navigation_tab_layout.setTabSelected(currentIndex)
        }
        currentIndex = position
    }

    private fun selectTab(position: Int) {
        currentIndex = position
        recyclerView.stopScroll()
        smoothScrollToPosition(position)
    }

    private fun smoothScrollToPosition(position: Int) {
        val firstPosition: Int = mLinearLayoutManager.findFirstVisibleItemPosition()
        val lastPosition: Int = mLinearLayoutManager.findLastVisibleItemPosition()
        when {
            position <= firstPosition -> { //滑动的距离超过一屏且向上滑动情况
                recyclerView.smoothScrollToPosition(position)
            }
            position <= lastPosition -> { //滑动的距离没超过一屏firstPosition<position<lastPosition,所以就是向上滑动position-firstPosition的距离
                val top: Int = recyclerView.getChildAt(position - firstPosition).top
                recyclerView.smoothScrollBy(0, top)
            }
            else -> { //滑动的距离超过一屏且向下滑动情况
                recyclerView.smoothScrollToPosition(position)
                bScroll = true
            }
        }
    }

    override fun setNavigation(data: MutableList<NavigationBean>) {
        showNormal()
        // 刷新右侧界面
        mData.addAll(data)
        mAdapter.setListAll(mData)
        // 刷新左侧界面
        navigation_tab_layout.run {
            setTabAdapter(NavigationTabAdapter(context, data))
        }
    }
}