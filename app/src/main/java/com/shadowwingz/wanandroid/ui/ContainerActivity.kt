package com.shadowwingz.wanandroid.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.shadowwingz.wanandroid.R
import com.shadowwingz.wanandroid.utils.KeepStateNavigator
import kotlinx.android.synthetic.main.activity_container.*

class ContainerActivity : AppCompatActivity() {
  
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_container)
    
    val navController = Navigation.findNavController(this, R.id.fragmentBottomNav)
    val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentBottomNav)!!
    val navigator = KeepStateNavigator(this, navHostFragment.childFragmentManager, R.id.fragmentBottomNav)
    navController.navigatorProvider.addNavigator(navigator)

    val config = AppBarConfiguration.Builder(bottomNavigationView.menu).build()
    NavigationUI.setupActionBarWithNavController(this, navController, config)
    navController.setGraph(R.navigation.bottom_nav)
    NavigationUI.setupWithNavController(bottomNavigationView, navController)
  }
  
}