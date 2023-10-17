package com.laura.splitthebill.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.laura.splitthebill.R
import com.laura.splitthebill.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val amb: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}