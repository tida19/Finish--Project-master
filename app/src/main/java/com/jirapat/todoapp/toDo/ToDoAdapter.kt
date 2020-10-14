package com.jirapat.todoapp.toDo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jirapat.todoapp.R
import com.jirapat.todoapp.database.Activity

class ToDoAdapter : RecyclerView.Adapter<ToDoAdapter.ViewHolder>() {

    var data = listOf<Activity>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = data[position]

        holder.bind(item)
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)

        val view = layoutInflater
            .inflate(R.layout.list_item, parent, false)

        return ViewHolder(view)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameText: TextView = itemView.findViewById(R.id.name_text)


        fun bind(item: Activity) {
            val res = itemView.context.resources
            nameText.text = item.activityName.toString()
        }

    }
}