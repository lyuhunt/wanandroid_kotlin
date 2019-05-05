package beyondsoft.com.wanandroid.mvp.presenter

import android.util.Log
import beyondsoft.com.wanandroid.api.ApiService
import beyondsoft.com.wanandroid.api.ApiStore
import beyondsoft.com.wanandroid.api.Transformer
import beyondsoft.com.wanandroid.base.BasePresenter
import beyondsoft.com.wanandroid.mvp.contract.LoginContract
import beyondsoft.com.wanandroid.mvp.model.bean.HttpResult
import beyondsoft.com.wanandroid.mvp.model.bean.LoginData
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONObject

class LoginPresenter : BasePresenter<LoginContract.View>(), LoginContract.Presenter {

    override fun login(username: String, password: String) {
        val map = hashMapOf<String, String>()
        map["username"] = username
        map["password"] = password
        Log.e("TAG", "map=$map")
        val json = JSONObject(map)
        val requestBody = RequestBody.create(
                MediaType.parse("application/json"), json.toString())
        ApiStore.createApi(ApiService::class.java)
                ?.loginWanAndroid(json.toString())
                ?.compose(Transformer.switchSchedulers())
                ?.subscribe(object : Observer<HttpResult<LoginData>>{
                    override fun onComplete() {
                        Log.e("TAG", "onComplete")
                    }

                    override fun onSubscribe(d: Disposable) {
                        Log.e("TAG", "onSubscribe")
                        mRxManager.add(d)
                    }

                    override fun onNext(t: HttpResult<LoginData>) {
                        Log.e("TAG", "onNext=${t}")
                        if (isAttachView()) {
                            if (t.data != null) {
                                mView?.loginSuccess(t.data)
                            }
                        }
                    }

                    override fun onError(e: Throwable) {
                        Log.e("TAG", "onError=${e.message}")
                    }
                })
    }

}