package beyondsoft.com.wanandroid.ui.activity

import beyondsoft.com.wanandroid.R
import beyondsoft.com.wanandroid.base.BaseMvpActivity
import beyondsoft.com.wanandroid.mvp.contract.LoginContract
import beyondsoft.com.wanandroid.mvp.model.bean.LoginData
import beyondsoft.com.wanandroid.mvp.presenter.LoginPresenter

class LoginActivity : BaseMvpActivity<LoginContract.View, LoginContract.Presenter>(), LoginContract.View{

    override fun createPresenter(): LoginContract.Presenter = LoginPresenter()

    override fun reload() {
    }

    override fun loginSuccess(data: LoginData) {
    }

    override fun loginFail() {
    }

    override fun getData() {
    }

    override fun initData() {
    }

    override fun getLayoutRes(): Int = R.layout.activity_login

}