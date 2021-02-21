package com.shadowwingz.wanandroid.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.shadowwingz.wanandroid.R
import kotlinx.android.synthetic.main.activity_container.*

class ContainerActivity : AppCompatActivity() {
  
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_container)
    
    val navController = Navigation.findNavController(this, R.id.fragmentBottomNav)
    val config = AppBarConfiguration.Builder(bottomNavigationView.menu).build()
    NavigationUI.setupActionBarWithNavController(this, navController, config)
    NavigationUI.setupWithNavController(bottomNavigationView, navController)
  }
  
}