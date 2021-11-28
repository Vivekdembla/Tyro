package com.security.tyro

import android.content.res.Resources
import android.media.Image
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var drawerLayout: DrawerLayout
    lateinit var navigation_view: NavigationView
    lateinit var openDrawer: ImageView
    lateinit var duration_tick:ImageView
    lateinit var time : ImageView
    lateinit var trainer_layout: ConstraintLayout
    lateinit var trainer_option: ImageView
    lateinit var difficulty_options : ImageView
    lateinit var difficulty_layout : ConstraintLayout
    lateinit var recyclerView :RecyclerView
    lateinit var mAdapter : ItemAdapter
    lateinit var trainer1:ImageView
    lateinit var trainer2:ImageView
    lateinit var trainer3:ImageView
    lateinit var difficulty1:ImageView
    lateinit var difficulty2:ImageView
    lateinit var difficulty3:ImageView
    lateinit var back:ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = ContextCompat.getColor(this, R.color.black)
        }
        openDrawer = findViewById(R.id.openDrawer)
        navigation_view = findViewById(R.id.navigation_view)
        drawerLayout = findViewById<View>(R.id.my_drawer_layout) as DrawerLayout
        duration_tick = findViewById(R.id.duration_options)
        time = findViewById(R.id.time)
        trainer_layout = findViewById(R.id.trainer_layout)
        difficulty_layout = findViewById(R.id.difficulty_layout)
        trainer_option = findViewById(R.id.trainer_option)
        difficulty_options = findViewById(R.id.difficulty_options)
        recyclerView = findViewById(R.id.recyclerView)
        trainer1 = findViewById(R.id.trainer1)
        trainer2 = findViewById(R.id.trainer2)
        trainer3 = findViewById(R.id.trainer3)
        difficulty1 = findViewById(R.id.difficulty1)
        difficulty2 = findViewById(R.id.difficulty2)
        difficulty3 = findViewById(R.id.difficulty3)
        back = findViewById(R.id.back)
        mAdapter = ItemAdapter(this)
        recyclerView.layoutManager = GridLayoutManager(this, 3)

        val BaseUrl = "http://3.108.207.62:3003/api/"
        val retrofit = Retrofit.Builder()
            .baseUrl(BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(execiseService::class.java)
        val call = service.getExerciseData()
        call.enqueue(object : Callback<ExerciseResponse> {
            override fun onResponse(call: Call<ExerciseResponse>, response: Response<ExerciseResponse>) {
                if (response.code() == 200) {
                    val exerciseResponse = response.body()!!
                    val recyclerViewData: recyclerViewData = recyclerViewData()
                    for (item in exerciseResponse.data!!){
                        recyclerViewData.trainerName = item.trainer_name
                        recyclerViewData.duration = "${item.duration} Min"
                        recyclerViewData.exerciseName = item.name
                        recyclerViewData.level = item.difficulty_level_name
                        mAdapter.add(recyclerViewData)
                    }
                }
            }
            override fun onFailure(call: Call<ExerciseResponse>, t: Throwable) {
                Log.e("Checking","Error in Fetching")
            }
        })
        recyclerView.adapter = mAdapter

        val width: Int = Resources.getSystem().displayMetrics.widthPixels
        val widthOfNav = (width) * 0.48
        navigation_view.setNavigationItemSelectedListener(this)
        navigation_view.layoutParams.width = widthOfNav.toInt()
        navigation_view.requestLayout()
        val done_button = findViewById<ImageView>(R.id.done_button)
        done_button.setOnClickListener {
            drawerLayout.closeDrawer(GravityCompat.END)
            Toast.makeText(this,"Filtered",Toast.LENGTH_LONG).show()
        }
        back.setOnClickListener {
            Toast.makeText(this,"Back Clicked",Toast.LENGTH_LONG).show()
        }

        trainer_option.setOnClickListener {
            if(trainer_layout.visibility == View.VISIBLE){
                trainer_layout.visibility = View.GONE
                trainer_option.setBackgroundResource(R.drawable.down_arrow)
            }else{
                trainer_layout.visibility = View.VISIBLE
                trainer_option.setBackgroundResource(R.drawable.up_arrow)
            }
        }

        difficulty_options.setOnClickListener {
            if(difficulty_layout.visibility == View.VISIBLE){
                difficulty_layout.visibility = View.GONE
                difficulty_options.setBackgroundResource(R.drawable.down_arrow)
            }else{
                difficulty_layout.visibility = View.VISIBLE
                difficulty_options.setBackgroundResource(R.drawable.up_arrow)
            }
        }

        duration_tick.setOnClickListener {
            if(time.visibility == View.VISIBLE){
                time.visibility = View.GONE
                duration_tick.setBackgroundResource(R.drawable.down_arrow)
            }else{
                time.visibility = View.VISIBLE
                duration_tick.setBackgroundResource(R.drawable.up_arrow)
            }
        }
        trainer1.setOnClickListener {
            trainer2.setImageResource(R.drawable.unchecked_box)
            trainer3.setImageResource(R.drawable.unchecked_box)
            trainer1.setImageResource(R.drawable.checked_box)
        }
        trainer2.setOnClickListener {
            trainer1.setImageResource(R.drawable.unchecked_box)
            trainer3.setImageResource(R.drawable.unchecked_box)
            trainer2.setImageResource(R.drawable.checked_box)
        }
        trainer3.setOnClickListener {
            trainer2.setImageResource(R.drawable.unchecked_box)
            trainer1.setImageResource(R.drawable.unchecked_box)
            trainer3.setImageResource(R.drawable.checked_box)
        }
        difficulty1.setOnClickListener {
            difficulty1.setImageResource(R.drawable.checked_box)
            difficulty2.setImageResource(R.drawable.unchecked_box)
            difficulty3.setImageResource(R.drawable.unchecked_box)
        }
        difficulty2.setOnClickListener {
            difficulty2.setImageResource(R.drawable.checked_box)
            difficulty1.setImageResource(R.drawable.unchecked_box)
            difficulty3.setImageResource(R.drawable.unchecked_box)
        }
        difficulty3.setOnClickListener {
            difficulty3.setImageResource(R.drawable.checked_box)
            difficulty2.setImageResource(R.drawable.unchecked_box)
            difficulty1.setImageResource(R.drawable.unchecked_box)
        }

        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        openDrawer.setOnClickListener {
            if(drawerLayout.isDrawerOpen(GravityCompat.END)){
                drawerLayout.closeDrawer(GravityCompat.END)
            }
            drawerLayout.openDrawer(GravityCompat.END)
        }


    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        return true
    }

}