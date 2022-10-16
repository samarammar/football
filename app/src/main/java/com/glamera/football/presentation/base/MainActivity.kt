package com.glamera.football.presentation.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.glamera.football.R
import com.glamera.football.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment?
        val navController = navHostFragment!!.navController
//        NavigationUI.setupWithNavController(this,navController)

        

//        navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main)
//        NavigationUI.setupActionBarWithNavController(this, navController)

//        val navController = findNavController(R.id.nav_host_fragment_activity_main)
//        NavigationUI.setupActionBarWithNavController(this, navController)

//
//        binding..setupWithNavController(navController)
    }
}