package com.albertjsoft.cubes.ui.cubes.list

import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.View
import com.albertjsoft.cubes.models.Cube
import io.reactivex.subjects.PublishSubject

import kotlinx.android.synthetic.main.item_cube.view.*


class CubeViewHolder(private var view: View, clickSubject: PublishSubject<Int>) : RecyclerView.ViewHolder(view) {

    init {
        view.setOnClickListener { clickSubject.onNext(adapterPosition) }
    }

    internal fun bind(cube: Cube) {
        view.item_cube_name.text = if (TextUtils.isEmpty(cube.name)) "missing name" else cube.name
        view.item_cube_description.text = if (TextUtils.isEmpty(cube.description)) "missing description" else cube.description
    }

}