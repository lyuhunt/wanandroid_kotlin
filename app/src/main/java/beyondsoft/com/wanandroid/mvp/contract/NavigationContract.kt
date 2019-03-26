package beyondsoft.com.wanandroid.mvp.contract

import beyondsoft.com.wanandroid.base.IPresenter
import beyondsoft.com.wanandroid.base.IView
import beyondsoft.com.wanandroid.mvp.model.bean.NavigationBean

/**
 * @author zhaixiaofan
 * @date 2019/3/17 7:25 PM
 */

class NavigationContract {

    interface View: IView {
        fun setNavigation(data : MutableList<NavigationBean>)
    }

    interface Presenter : IPresenter<View> {
        fun getNavigation()
    }
}