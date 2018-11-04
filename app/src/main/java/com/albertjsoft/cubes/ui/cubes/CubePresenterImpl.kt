package com.albertjsoft.cubes.ui.cubes

import com.albertjsoft.cubes.data.api.CubeService
import com.albertjsoft.cubes.data.api.observer.ApiObserver
import com.albertjsoft.cubes.data.api.response.CubeListResponse
import com.albertjsoft.cubes.util.apiSubscribe
import io.reactivex.Observable
import org.reactivestreams.Subscription

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
            }

            override fun onError(exception: Throwable) {
                super.onError(exception)
            }
        })
    }

    private fun respondToClick(): Subscription {
        val click: Observable<Int> = cubeView.itemClicks()
        //TODO: DO SOMETHING

    }
}