package com.albertjsoft.cubes.ui.cubes

import javax.inject.Inject

class CubePresenterImpl @Inject constructor() : CubePresenter {

    private lateinit var cubeView: CubeView

    override fun setView(cubeView: CubeView) {
        this.cubeView = cubeView
    }

    override fun loadCubes() {
        //TODO: LOAD CUBES
    }
}