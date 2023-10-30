package com.laura.splitthebill.view

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.laura.splitthebill.databinding.PersonDescBinding
import com.laura.splitthebill.model.Constant.EXTRA_PERSON
import com.laura.splitthebill.model.Constant.VIEW_PERSON
import com.laura.splitthebill.model.Person

class PersonDetailsActivity: AppCompatActivity() {
    private val apdb: PersonDescBinding by lazy {
        PersonDescBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(apdb.root)

        val receivedPerson = intent.getParcelableExtra<Person>(EXTRA_PERSON)
        receivedPerson?.let { _receivedPerson ->
            val viewPerson: Boolean = intent.getBooleanExtra(VIEW_PERSON, false)
            with(apdb) {
                if(viewPerson) {
                    nameEt.isEnabled = false
                    totalPriceEt.isEnabled = false
                    saveBt.visibility = View.GONE
                }
                nameEt.setText(_receivedPerson.name)
                totalPriceEt.setText(_receivedPerson.totalPricePaid.toString())
            }
        }
    }
}