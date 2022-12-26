package ir.aminrahkan.presentation.ui.main

import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import ir.aminrahkan.presentation.R
import ir.aminrahkan.presentation.ui.base.BaseActivity

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}