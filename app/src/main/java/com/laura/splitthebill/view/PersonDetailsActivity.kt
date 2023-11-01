package com.laura.splitthebill.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.laura.splitthebill.databinding.PersonDescBinding
import com.laura.splitthebill.model.Constant.EXTRA_PAYMENTS
import com.laura.splitthebill.model.Constant.EXTRA_PERSON
import com.laura.splitthebill.model.Constant.VIEW_PERSON
import com.laura.splitthebill.model.Person
import java.util.Random

class PersonDetailsActivity: AppCompatActivity() {
    private val apdb: PersonDescBinding by lazy {
        PersonDescBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(apdb.root)

        val receivedPerson = intent.getParcelableExtra<Person>(EXTRA_PERSON)
        val receivedPayments = intent.getParcelableExtra<Person>(EXTRA_PAYMENTS)

        receivedPerson?.let { _receivedPerson ->
            val viewPerson: Boolean = intent.getBooleanExtra(VIEW_PERSON, false)
            with(apdb) {
                if(viewPerson) {
                    nameEt.isEnabled = false
                    itemOneNameEt.isEnabled = false
                    itemOnePriceEt.isEnabled = false
                    itemTwoNameEt.isEnabled = false
                    itemTwoPriceEt.isEnabled = false
                    itemThreeNameEt.isEnabled = false
                    itemThreePriceEt.isEnabled = false
                    paymentTv.setText(receivedPayments.toString())
                    saveBt.visibility = View.GONE
                }
                nameEt.setText(_receivedPerson.name)

                itemOneNameEt.setText(_receivedPerson.itemOneName)
                itemOnePriceEt.setText(_receivedPerson.itemOnePrice.toString())

                itemTwoNameEt.setText(_receivedPerson.itemTwoName)
                itemTwoPriceEt.setText(_receivedPerson.itemTwoPrice.toString())

                itemThreeNameEt.setText(_receivedPerson.itemThreeName)
                itemThreePriceEt.setText(_receivedPerson.itemThreePrice.toString())

                totalSpentEt.setText(calculateTotalSpent(_receivedPerson).toString())

            }
        }

        with (apdb) {
            saveBt.setOnClickListener {
                val person = Person(
                    id = receivedPerson?.id,
                    name = nameEt.text.toString(),
                    itemOneName = returnNoDescNameToEmptyValues(itemOneNameEt.text.toString()),
                    itemOnePrice = returnZeroToEmptyValues(itemOnePriceEt.text.toString()),

                    itemTwoName = returnNoDescNameToEmptyValues(itemTwoNameEt.text.toString()),
                    itemTwoPrice = returnZeroToEmptyValues(itemTwoPriceEt.text.toString()),

                    itemThreeName = returnNoDescNameToEmptyValues(itemThreeNameEt.text.toString()),
                    itemThreePrice = returnZeroToEmptyValues(itemThreePriceEt.text.toString())
                )

                val resultIntent = Intent()
                resultIntent.putExtra(EXTRA_PERSON, person)
                setResult(RESULT_OK, resultIntent)
                finish()
            }
        }
    }

    private fun calculateTotalSpent(person: Person): Double {
        val itemOnePrice = person.itemOnePrice
        val itemTwoPrice = person.itemTwoPrice
        val itemThreePrice = person.itemThreePrice

        return itemOnePrice + itemTwoPrice + itemThreePrice
    }

    private fun returnZeroToEmptyValues(price: String): Double {
        if (price.isEmpty() || price.isBlank()) return 0.0
        return price.toDouble()
    }

    private fun returnNoDescNameToEmptyValues(name: String): String {
        if (name.isEmpty() || name.isBlank()) return "N/A"
        return name
    }

    private fun generateId(): Int = Random(System.currentTimeMillis()).nextInt()
}