package com.albertjsoft.cubes.data.api.response

import com.albertjsoft.cubes.models.Cube
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CubeListResponse{
    @SerializedName("boards")
    @Expose
    private var boards: List<Cube>? = null

    fun getData(): List<Cube> = boards!!
}