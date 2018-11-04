package com.albertjsoft.cubes.data.api

import com.albertjsoft.cubes.models.Cube
import io.reactivex.Observable
import retrofit2.http.*

interface CubeService{
    @GET("/api/board/{boardId}")
    fun getCube(@Path("boardId") boardId: Int): Observable<Cube>
}