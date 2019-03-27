package beyondsoft.com.wanandroid.ui.activity

import android.support.v7.widget.SearchView
import android.util.Log
import android.view.Menu
import android.widget.ImageView
import beyondsoft.com.wanandroid.R
import beyondsoft.com.wanandroid.base.BaseActivity
import kotlinx.android.synthetic.main.toolbar.*

class SearchActivity : BaseActivity() {

    val TAG = "SearchActivity"

    override fun initView() {
        toolbar.run {
            setSupportActionBar(this)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            setNavigationOnClickListener {
                finish()
            }
        }
    }

    override fun initData() {
    }

    override fun getLayoutRes(): Int = R.layout.activity_search

    override fun getData() {
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        val searchView = menu?.findItem(R.id.action_search)?.actionView as SearchView
        searchView.maxWidth = Integer.MAX_VALUE
        searchView.onActionViewExpanded()
        searchView.queryHint = getString(R.string.search_tint)
        searchView.setOnQueryTextListener(queryTextListener)
        searchView.isSubmitButtonEnabled = true
        try {
            val field = searchView.javaClass.getDeclaredField("mGoButton")
            field.isAccessible = true
            val mGoButton = field.get(searchView) as ImageView
            mGoButton.setImageResource(R.drawable.ic_search_white_24dp)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return super.onCreateOptionsMenu(menu)
    }

    //搜索的点击事件
    private val queryTextListener = object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            Log.e(TAG, "onQueryTextSubmit")
            return false
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            Log.e(TAG, "onQueryTextChange")
            return false
        }
    }

}