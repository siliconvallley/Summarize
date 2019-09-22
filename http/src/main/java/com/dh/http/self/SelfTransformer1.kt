package com.dh.http.self

import com.dh.http.BaseDataF
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * @author: 86351
 * @date: 2019/3/10
 * @description: 复用共同操作
 * @version:
 */
class SelfTransformer1 : ObservableTransformer<BaseDataF, BaseDataF> {
    override fun apply(upstream: Observable<BaseDataF>): ObservableSource<BaseDataF> {
        return upstream.subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}