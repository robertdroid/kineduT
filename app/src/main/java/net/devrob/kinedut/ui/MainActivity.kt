package net.devrob.kinedut.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import net.devrob.kinedut.R

class MainActivity : AppCompatActivity() {
    var currentFilter = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
