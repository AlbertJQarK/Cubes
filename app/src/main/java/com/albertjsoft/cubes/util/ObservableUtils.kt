package com.albertjsoft.cubes.util

import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> Observable<T>.observeAndSubscribeOn(): Observable<T> {
    return observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
}

fun <T> Observable<T>.apiSubscribe(observer: Observer<in T>) {
    observeAndSubscribeOn().subscribe(observer)
}