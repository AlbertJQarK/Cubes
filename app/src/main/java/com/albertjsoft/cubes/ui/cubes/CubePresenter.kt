package com.albertjsoft.cubes.ui.cubes

import com.albertjsoft.cubes.data.api.CubeService


interface CubePresenter {
    fun setView(cubeView: CubeView)
    fun setService(service: CubeService)
    fun loadCubes()
}