package com.security.tyro

class ExerciseResponse     {
    var success:Boolean=false
    var message : String =""
    var data: ArrayList<dataString>?=null
}

class dataString {
    var id: Int?=0
    var name= "Unknown"
    var category_id= 14
    var trainer_id = 9
    var image = "1634042874336.png"
    var duration = 2400
    var min_calories= 420
    var max_calories = 460
    var description = "This is a class for cardio"
    var difficulty_level= 2
    var createdAt = 1632466990
    var updatedAt = null
    var video = "1634042874323.mp4"
    var video_uploadedAt = null
    var equipments: ArrayList<String>? = null
    var workout_plans:ArrayList<workplan>?=null
    var trainer_name = "Ankit"
    var category_name = "Cardio"
    var difficulty_level_name = "Intermediate"

}

class workplan {
    var name:String = ""
    var reps:Int = 0
}
