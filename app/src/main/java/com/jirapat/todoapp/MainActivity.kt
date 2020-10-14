package com.jirapat.todoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.RecyclerView
import com.jirapat.todoapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var Menu: RecyclerView? = null
    private var foods = arrayOf(
        "ชาเขียวน้ำผึ้งมะนาว",
        "ชาดำเย็น",
        "ชาเขียวลาเต้"


    )
    private var arrImg = arrayOf(

        R.drawable.shutterstock_280684463_1,
        R.drawable.tomyam,
        R.drawable.hc_cuisine_14s,
        R.drawable.happyfresh_thai_green_curry_with_chicken_940x700,
        R.drawable.unnamed,
        R.drawable.unnamed__1_


    )

    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this,
            R.layout.activity_main)

        drawerLayout = binding.drawerLayout
        val navController = this.findNavController(R.id.myNavHostFragment)
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
        NavigationUI.setupWithNavController(binding.navView, navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavHostFragment)
        return NavigationUI.navigateUp(navController, drawerLayout)
    }
}