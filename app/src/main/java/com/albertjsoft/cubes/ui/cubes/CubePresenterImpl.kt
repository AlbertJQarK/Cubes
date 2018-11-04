package com.albertjsoft.cubes.ui.cubes

import com.albertjsoft.cubes.data.api.CubeService
import com.albertjsoft.cubes.data.api.observer.ApiObserver
import com.albertjsoft.cubes.models.Cube
import com.albertjsoft.cubes.util.apiSubscribe
import io.reactivex.Observable

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
        var call: Observable<Cube> = cubeService.getCube(1)
        call.apiSubscribe(object : ApiObserver<Cube>() {
            override fun onSuccess(response: Cube) {
                //TODO: ON SUCCESS
            }

            override fun onError(exception: Throwable) {
                super.onError(exception)
            }
        })
    }
}