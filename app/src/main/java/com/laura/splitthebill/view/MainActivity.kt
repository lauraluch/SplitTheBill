package com.laura.splitthebill.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.person_menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.addPersonMi -> {
                parl.launch(Intent(this,PersonDetailsActivity::class.java))
                true
            }
            else -> true
        }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        menuInflater.inflate(R.menu.person_menu_main, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val position = (item.menuInfo as AdapterView.AdapterContextMenuInfo).position
        val person = peopleList[position]

        return when (item.itemId){
            R.id.removePersonMi -> {
                // Revisar
                personController.removePerson(person.id!!)
                Toast.makeText(this,"Pessoa removida da lista", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.editPersonMi -> {
                val personToEdit = peopleList[position]
                val editPersonIntent = Intent(this, PersonDetailsActivity::class.java)
                editPersonIntent.putExtra(EXTRA_PERSON, personToEdit)
                parl.launch(editPersonIntent)
                true
            }
            else -> {true}
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterForContextMenu(amb.peopleLv)
    }
}