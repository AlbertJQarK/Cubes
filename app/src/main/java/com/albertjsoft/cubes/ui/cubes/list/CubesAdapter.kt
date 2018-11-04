package com.albertjsoft.cubes.ui.cubes.list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.albertjsoft.cubes.models.Cube
import io.reactivex.subjects.PublishSubject
import io.reactivex.Observable

import com.albertjsoft.cubes.R

class CubesAdapter(private val listCubes: List<Cube>) : RecyclerView.Adapter<CubeViewHolder>() {

    private val itemClicks: PublishSubject<Int> = PublishSubject.create()
    private val itemCount: Int = listCubes.size

    override fun getItemCount(): Int {
        return itemCount
    }

    override fun onBindViewHolder(holder: CubeViewHolder, position: Int) {
        val cube = listCubes[position]
        holder.bind(cube)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CubeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cube, parent, false)
        return CubeViewHolder(view, itemClicks)
    }

    fun observeClicks(): Observable<Int> {
        return itemClicks
    }

/**
    fun resetAdapter(cubes: ArrayList<Cube>) {
        this.listCubes!!.clear()
        this.listCubes!!.addAll(cubes)
        notifyDataSetChanged()
    }
**/
}