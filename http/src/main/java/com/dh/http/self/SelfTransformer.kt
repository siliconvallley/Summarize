package com.dh.http.self

import com.dh.http.BaseData
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * @author huidingc
 * @date 2018/11/7
 * @description SelfTransformer 复用共同操作
 * @version
 */
class SelfTransformer<T>:ObservableTransformer<BaseData<T>, BaseData<T>> {
    override fun apply(upstream: Observable<BaseData<T>>): ObservableSource<BaseData<T>> {
        return upstream.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}