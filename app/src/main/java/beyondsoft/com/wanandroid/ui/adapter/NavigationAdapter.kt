package beyondsoft.com.wanandroid.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import beyondsoft.com.wanandroid.R
import beyondsoft.com.wanandroid.mvp.model.bean.Article
import beyondsoft.com.wanandroid.mvp.model.bean.NavigationBean
import com.zhouyou.recyclerview.adapter.HelperRecyclerViewAdapter
import com.zhouyou.recyclerview.adapter.HelperRecyclerViewHolder
import com.zhy.view.flowlayout.FlowLayout
import com.zhy.view.flowlayout.TagAdapter
import com.zhy.view.flowlayout.TagFlowLayout

class NavigationAdapter(var context: Context) : HelperRecyclerViewAdapter<NavigationBean>(context, R.layout.item_navigation) {

    override fun HelperBindData(viewHolder: HelperRecyclerViewHolder?, position: Int, item: NavigationBean?) {

        item.let {
            viewHolder!!.run {
                setText(R.id.item_navigation_tv, it!!.name)
                var tag = getView(R.id.item_navigation_flow_layout) as TagFlowLayout

                tag.adapter = object : TagAdapter<Article>(it.articles) {
                    override fun getView(parent: FlowLayout?, position: Int, t: Article?): View? {
                        val textView = LayoutInflater.from(context).inflate(R.layout.item_navigation_tag, tag, false) as TextView
                        t ?: return null
                        textView.text = t.title
                        return textView
                    }
                }
            }
        }
    }

}