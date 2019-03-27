package beyondsoft.com.wanandroid.mvp.contract

import beyondsoft.com.wanandroid.base.IPresenter
import beyondsoft.com.wanandroid.base.IView

/**
 * 收藏和取消收藏的Contract,由于好几个界面会有收藏功能，故抽取出来
 * 其它有收藏功能界面的Contract只需继承CommonContract
 *
 * 同时CommonContract亦必须有他对应的CommonPresenter(详见CommonPresenter)
 */
class CommonContract {

    interface View : IView {
        fun showCollectSuccess(success: Boolean)
        fun showCancelCollectSuccess(success: Boolean)
    }

    /**
     * 该处Presenter会有子interface继承，故泛型V得参数话，不能写死，
     * 此处使用泛型通配符
     */
    interface Presenter<V : View> : IPresenter<V> {
        fun addCollectArticle(id: Int)
        fun cancelCollectArticle(id: Int)
    }
}