package com.security.tyro

import retrofit2.Call
import retrofit2.http.GET

interface execiseService {
    @GET("user/workout/all?category_id=14")
    fun getExerciseData(): Call<ExerciseResponse>
}