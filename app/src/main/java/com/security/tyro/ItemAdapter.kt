package com.security.tyro

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class ItemAdapter(var listener : Context): RecyclerView.Adapter<ExerciseViewHolder>() {
    private val items: ArrayList<recyclerViewData> = ArrayList()

    fun add(list: recyclerViewData) {
        items.add(list)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        val viewHolder = ExerciseViewHolder(view)
        view.setOnClickListener {

        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        val currentItem = items[position]
        holder.exercise_name.text = currentItem.exerciseName
        holder.duration.text = currentItem.duration
        holder.level.text = currentItem.level
        holder.trainer_name.text = "With ${currentItem.trainerName}"
        if((position-1)%3==0)
        holder.decortion.setImageDrawable(ContextCompat.getDrawable(listener, R.drawable.ladki))
    }

    override fun getItemCount(): Int {
        return items.size
    }
}

class ExerciseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val exercise_name = itemView.findViewById<TextView>(R.id.exercise_name)
    val trainer_name = itemView.findViewById<TextView>(R.id.trainer_name)
    val level = itemView.findViewById<TextView>(R.id.level)
    val duration = itemView.findViewById<TextView>(R.id.time_taken)
    val decortion = itemView.findViewById<ImageView>(R.id.decoration)
}

