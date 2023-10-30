package com.laura.splitthebill.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.laura.splitthebill.R
import com.laura.splitthebill.adapter.PersonAdapter
import com.laura.splitthebill.controller.PersonController
import com.laura.splitthebill.databinding.ActivityMainBinding
import com.laura.splitthebill.model.Person

class MainActivity : AppCompatActivity() {
    private val amb: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val peopleList: MutableList<Person> = mutableListOf()

    private val personController: PersonController by lazy {
        PersonController(this)
    }

    private val personAdapter: PersonAdapter by lazy {
        PersonAdapter(
            this,
            peopleList
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}