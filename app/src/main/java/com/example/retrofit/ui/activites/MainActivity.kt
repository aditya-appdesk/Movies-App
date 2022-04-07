package com.example.retrofit.ui.activites

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.retrofit.R
import com.example.retrofit.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // This is function is for setting up the custom ActionBar
        setSupportActionBar(binding.toolbar)

        //set up for Bottom navigationView with fragment container
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottoNav.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, _, args ->
            val showActionBar = args?.getBoolean("showActionBar", true) == true
            val showBottomNav = args?.getBoolean("showBottomNav", true) == true

            // Show/Hide the Action bar based on arg
            supportActionBar?.apply {
                if (showActionBar) show()
                else hide()
            }
            // Set visibility for bottom nav view
            binding.bottoNav.isVisible = showBottomNav
        }
    }

    // Custom Menu Setup
    /*override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }*/

    // Custom Menu Click Handle
    /* override fun onOptionsItemSelected(item: MenuItem): Boolean {
         if (item.itemId == R.id.refresh) {
             Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show()
         }
         return super.onOptionsItemSelected(item)
     }*/
}