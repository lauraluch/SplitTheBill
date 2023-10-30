package com.laura.splitthebill.view

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.laura.splitthebill.databinding.PersonDescBinding
import com.laura.splitthebill.model.Person

class PersonDetailsActivity: AppCompatActivity() {
    private val apdb: PersonDescBinding by lazy {
        PersonDescBinding.inflate(layoutInflater)
    }
}