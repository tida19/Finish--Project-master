package com.jirapat.todoapp.toDo

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.google.android.material.snackbar.Snackbar
import com.jirapat.todoapp.R
import com.jirapat.todoapp.database.ActivityDatabase
import com.jirapat.todoapp.databinding.FragmentToDoBinding


class ToDoFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<FragmentToDoBinding>(
            inflater,
            R.layout.fragment_to_do,
            container,
            false
        )

        setHasOptionsMenu(true)
        val adapter = ToDoAdapter()

        val application = requireNotNull(this.activity).application
        val dataSource = ActivityDatabase.getInstance(application).activityDao
        val viewModelFactory = ToDoViewModelFactory(dataSource, binding, application)
        val toDoViewModel =
            ViewModelProvider(
                this, viewModelFactory
            ).get(ToDoViewModel::class.java)

        toDoViewModel.activity.observe(viewLifecycleOwner, Observer{
            it?.let {
                adapter.data = it
            }
        })

        binding.toDoViewModel = toDoViewModel
        binding.lifecycleOwner = this
        binding.activityList.adapter = adapter



        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.option_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(
            item!!,
            view!!.findNavController()
        ) || super.onOptionsItemSelected(item)
    }


}

