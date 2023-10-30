package com.laura.splitthebill.view

import androidx.appcompat.app.AppCompatActivity
import com.laura.splitthebill.databinding.PersonDescBinding

class PersonDetailsActivity: AppCompatActivity() {
    private val acb: PersonDescBinding by lazy {
        PersonDescBinding.inflate(layoutInflater)
    }
}