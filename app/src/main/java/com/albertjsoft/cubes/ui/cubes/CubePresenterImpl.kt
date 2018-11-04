package com.albertjsoft.cubes.ui.cubes

import com.albertjsoft.cubes.data.api.CubeService
import com.albertjsoft.cubes.data.api.observer.ApiObserver
import com.albertjsoft.cubes.data.api.response.CubeListResponse
import com.albertjsoft.cubes.models.Cube
import com.albertjsoft.cubes.util.apiSubscribe
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable


class CubePresenterImpl : CubePresenter {

    private lateinit var cubeView: CubeView
    private lateinit var cubeService: CubeService

    override fun setView(cubeView: CubeView) {
        this.cubeView = cubeView
    }

    override fun setService(service: CubeService) {
        cubeService = service
    }

    override fun loadCubes() {
        val call: Observable<CubeListResponse> = cubeService.getCubes()
        call.apiSubscribe(object : ApiObserver<CubeListResponse>() {
            override fun onSuccess(response: CubeListResponse) {
                cubeView.getCubes(response.getData())
                observeClick()
            }

            override fun onError(exception: Throwable) {
                super.onError(exception)
            }
        })
    }

    override fun loadCube(id: Int) {
        val call: Observable<Cube> = cubeService.getCube(id)
        call.apiSubscribe(object : ApiObserver<Cube>() {
            override fun onSuccess(response: Cube) {
                cubeView.showCube(response)
            }

            override fun onError(exception: Throwable) {
                super.onError(exception)
            }
        })
    }

    override fun observeClick() {
        cubeView.itemClicks().subscribe(object : Observer<Int> {
            override fun onSubscribe(d: Disposable) {}
            override fun onNext(response: Int) { loadCube(response) }
            override fun onError(exception: Throwable) {}
            override fun onComplete() {}
        })
    }
}