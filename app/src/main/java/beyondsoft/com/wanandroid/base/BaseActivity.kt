package beyondsoft.com.wanandroid.base

import android.app.ActivityManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import beyondsoft.com.wanandroid.R
import beyondsoft.com.wanandroid.utils.ActivityManagers
import beyondsoft.com.wanandroid.utils.SettingUtil
import beyondsoft.com.wanandroid.utils.StatusBarUtil

abstract class BaseActivity : AppCompatActivity() {

    /**
     * theme color
     */
    protected var mThemeColor: Int = SettingUtil.getColor()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutRes())
        ActivityManagers.push(this)
        initData()
        initView()
        getData()
    }

    abstract fun getData()

    abstract fun initView()

    abstract fun initData()

    abstract fun getLayoutRes(): Int

    override fun onDestroy() {
        super.onDestroy()
        ActivityManagers.remove(this)
    }

    override fun onResume() {
        super.onResume()
        initColor()
    }

    open fun initColor() {
        mThemeColor = if (!SettingUtil.getIsNightMode()) { //白天模式
            SettingUtil.getColor()
        } else { //夜间模式
            resources.getColor(R.color.colorPrimary)
        }
        StatusBarUtil.setColor(this, mThemeColor, 0)
        if (this.supportActionBar != null) {
            this.supportActionBar?.setBackgroundDrawable(ColorDrawable(mThemeColor))
        }
    }
}