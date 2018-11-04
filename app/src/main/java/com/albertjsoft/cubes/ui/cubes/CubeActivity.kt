package com.albertjsoft.cubes.ui.cubes

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.ArrayAdapter
import com.albertjsoft.cubes.R
import com.albertjsoft.cubes.app.CubesApp
import com.albertjsoft.cubes.data.api.CubeService
import com.albertjsoft.cubes.models.Cube
import com.albertjsoft.cubes.models.Item
import com.albertjsoft.cubes.ui.cubes.list.CubesAdapter
import javax.inject.Inject
import io.reactivex.Observable

class CubeActivity: AppCompatActivity(), CubeView {

    @Inject
    lateinit var presenter: CubePresenter
    @Inject
    lateinit var cubeService: CubeService
    private var list: RecyclerView? = null
    private var adapter: CubesAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as CubesApp).component.inject(this)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        renderView()
        setUpPresenter()
    }

    private fun renderView(){
        setContentView(R.layout.activity_cube_list)
        list = findViewById(R.id.cube_list_recycleview)
        list!!.layoutManager = LinearLayoutManager(this)
    }

    private fun setUpPresenter(){
        presenter.setView(this)
        presenter.setService(cubeService)
        presenter.loadCubes()
    }

    override fun getCubes(listCube: List<Cube>) {
        adapter = CubesAdapter(listCube)
        list!!.adapter = adapter
    }

    override fun itemClicks(): Observable<Int> {
        return adapter!!.observeClicks()
    }

    override fun showCube(cube: Cube){
        val builderSingle = AlertDialog.Builder(this@CubeActivity)
        builderSingle.setTitle(cube.name)
        val arrayAdapter = ArrayAdapter<String>(this@CubeActivity, android.R.layout.simple_list_item_1)
        for(item: Item in cube.items!!){
            arrayAdapter.add(item.url)
        }
        builderSingle.setAdapter(arrayAdapter,null)
        builderSingle.show()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}