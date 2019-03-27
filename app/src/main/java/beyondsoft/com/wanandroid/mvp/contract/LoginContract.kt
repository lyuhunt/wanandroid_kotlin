package beyondsoft.com.wanandroid.mvp.contract

import beyondsoft.com.wanandroid.base.IPresenter
import beyondsoft.com.wanandroid.base.IView
import beyondsoft.com.wanandroid.mvp.model.bean.LoginData

class LoginContract {

    interface View : IView {
        fun loginSuccess(data: LoginData)
        fun loginFail()
    }

    interface Presenter : IPresenter<View> {
        fun login(username: String, password: String)
    }
}