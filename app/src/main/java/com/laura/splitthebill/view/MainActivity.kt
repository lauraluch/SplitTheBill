package com.laura.splitthebill.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.laura.splitthebill.R
import com.laura.splitthebill.adapter.PersonAdapter
import com.laura.splitthebill.controller.PersonController
import com.laura.splitthebill.databinding.ActivityMainBinding
import com.laura.splitthebill.model.Constant.EXTRA_PERSON
import com.laura.splitthebill.model.Constant.VIEW_PERSON
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

    private lateinit var parl: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(amb.root)

        amb.peopleLv.adapter = personAdapter

        parl = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ){ result ->
            if (result.resultCode == RESULT_OK){
                val person = result.data?.getParcelableExtra<Person>(EXTRA_PERSON)
                person?.let { _person ->
                    if(peopleList.any { it.id == person.id }){
                        personController.editPerson(_person)
                    }else {
                        personController.insertPerson(_person)
                    }
                }
            }
        }

        amb.peopleLv.setOnItemClickListener{parent, view, position, id->
            val person = peopleList[position]
            val viewPersonIntent = Intent(this, PersonDetailsActivity::class.java)
                .putExtra(EXTRA_PERSON, person)
                .putExtra(VIEW_PERSON,true)

            startActivity(viewPersonIntent)
        }

        registerForContextMenu(amb.peopleLv)
        personController.getPeople()
    }
}