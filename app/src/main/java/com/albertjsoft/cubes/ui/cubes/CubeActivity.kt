package com.albertjsoft.cubes.ui.cubes

import android.app.Activity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.albertjsoft.cubes.R
import com.albertjsoft.cubes.app.CubesApp
import com.albertjsoft.cubes.data.api.CubeService
import javax.inject.Inject

class CubeActivity: Activity(), CubeView {

    @Inject
    lateinit var presenter: CubePresenter
    @Inject
    lateinit var cubeService: CubeService
    private var list: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as CubesApp).component.inject(this)

        renderView()

        presenter.setView(this)
        presenter.setService(cubeService)
        presenter.loadCubes()
    }

    fun renderView(){
        setContentView(R.layout.activity_cube_list)
        list = findViewById(R.id.cube_list_recycleview)
        list!!.layoutManager = LinearLayoutManager(this)
    }
}