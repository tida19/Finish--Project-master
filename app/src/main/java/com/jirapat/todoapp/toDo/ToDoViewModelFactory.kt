package com.jirapat.todoapp.toDo

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jirapat.todoapp.database.ActivityDAO
import com.jirapat.todoapp.databinding.FragmentToDoBinding

class ToDoViewModelFactory(
    private val dataSource: ActivityDAO,
    private val binding: FragmentToDoBinding,
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ToDoViewModel::class.java)){
            return ToDoViewModel(dataSource,binding,application) as T
        }
        throw IllegalArgumentException("Unknown")
    }
}