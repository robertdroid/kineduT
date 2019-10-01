package net.devrob.kinedut.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import net.devrob.kinedut.R

class MainActivity : AppCompatActivity() {
    var currentFilter = -1
    var currentPage = 0
    lateinit var navController: NavController
    private var searchListener: SearchListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = findNavController(R.id.nav_host_fragment)
    }

    override fun onBackPressed() {
        if (navController.currentDestination != null && navController.currentDestination!!.id == R.id.articleDetailFragment) {
            navController.navigate(R.id.action_articleDetailFragment_to_crawlingFragment)
        } else
            super.onBackPressed()
    }

    fun performSearch() {
        searchListener?.performSearch()
    }

    fun setSearchListener(listener: SearchListener) {
        this.searchListener = listener
    }

    fun getSearchListener() = this.searchListener

    interface SearchListener {
        fun performSearch()
    }
}
