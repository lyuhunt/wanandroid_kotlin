package beyondsoft.com.wanandroid.mvp.presenter

import beyondsoft.com.wanandroid.api.ApiService
import beyondsoft.com.wanandroid.api.ApiStore
import beyondsoft.com.wanandroid.api.Transformer
import beyondsoft.com.wanandroid.base.BasePresenter
import beyondsoft.com.wanandroid.constant.Constant
import beyondsoft.com.wanandroid.mvp.contract.CommonContract
import beyondsoft.com.wanandroid.mvp.model.bean.HttpResult
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * 具有收藏功能的Presenter,由于好几个界面会有收藏功能，故CommonPresenter为可继承的open
 * 泛型V也得参数化，其它界面的Presenter继承且实例化泛型V即可，详见HomePresenter
 */
open class CommonPresenter<V : CommonContract.View> : BasePresenter<V>(), CommonContract.Presenter<V> {

    override fun addCollectArticle(id: Int) {
        ApiStore.createApi(ApiService::class.java)
                ?.addCollectArticle(id)
                ?.compose(Transformer.switchSchedulers())
                ?.subscribe(object : Observer<HttpResult<Any>> {
                    override fun onComplete() {
                    }

                    override fun onSubscribe(d: Disposable) {
                        mRxManager.add(d)
                    }

                    override fun onNext(t: HttpResult<Any>) {
                        if (isAttachView()) {
                            if (t.errorCode == Constant.RESPOSE_CODE_SUCCESS) {
                                mView?.showCollectSuccess(true)
                            } else {
                                mView?.showCollectSuccess(false)
                            }
                        }
                    }

                    override fun onError(e: Throwable) {
                    }

                })
    }

    override fun cancelCollectArticle(id: Int) {
    }

}