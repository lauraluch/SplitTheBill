package com.laura.splitthebill.view

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.laura.splitthebill.databinding.PersonDescBinding
import com.laura.splitthebill.model.Constant.EXTRA_PERSON
import com.laura.splitthebill.model.Constant.VIEW_PERSON
import com.laura.splitthebill.model.Person

class PersonDetailsActivity: AppCompatActivity() {
    private val apdb: PersonDescBinding by lazy {
        PersonDescBinding.inflate(layoutInflater)
    }

    private lateinit var itemsAdapter: ArrayAdapter<String>
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

        with (apdb) {
            saveBt.setOnClickListener {
                val person = Person(
                    id = receivedPerson?.id,
                    name = nameEt.text.toString(),
                    totalPricePaid = totalPriceEt.text.toString().toDouble(),
                    // Mudar lista vazia utilizada apenas para teste
                    thingsBought = emptyList()
                )

                val resultIntent = Intent()
                resultIntent.putExtra(EXTRA_PERSON, person)
                setResult(RESULT_OK, resultIntent)
                finish()
            }
        }
    }
}