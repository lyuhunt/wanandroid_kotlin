package beyondsoft.com.wanandroid.mvp.presenter

import beyondsoft.com.wanandroid.api.ApiService
import beyondsoft.com.wanandroid.api.ApiStore
import beyondsoft.com.wanandroid.api.Transformer
import beyondsoft.com.wanandroid.base.BasePresenter
import beyondsoft.com.wanandroid.mvp.contract.LoginContract
import beyondsoft.com.wanandroid.mvp.model.bean.HttpResult
import beyondsoft.com.wanandroid.mvp.model.bean.LoginData
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

class LoginPresenter : BasePresenter<LoginContract.View>(), LoginContract.Presenter {

    override fun login(username: String, password: String) {
        ApiStore.createApi(ApiService::class.java)
                ?.loginWanAndroid(username, password)
                ?.compose(Transformer.switchSchedulers())
                ?.subscribe(object : Observer<HttpResult<LoginData>>{
                    override fun onComplete() {
                    }

                    override fun onSubscribe(d: Disposable) {
                        mRxManager.add(d)
                    }

                    override fun onNext(t: HttpResult<LoginData>) {
                    }

                    override fun onError(e: Throwable) {
                    }

                })

    }

}