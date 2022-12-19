package ir.aminrahkan.presentation.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ir.aminrahkan.presentation.R

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}