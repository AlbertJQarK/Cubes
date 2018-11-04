package com.albertjsoft.cubes.ui.cubes

import com.albertjsoft.cubes.models.Cube
import io.reactivex.Observable


interface CubeView {
    fun getCubes(listCubes: List<Cube>)
    fun itemClicks(): Observable<Int>
}