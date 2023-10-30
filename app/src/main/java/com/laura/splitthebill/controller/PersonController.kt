package com.laura.splitthebill.controller

import com.laura.splitthebill.model.Person
import com.laura.splitthebill.model.PersonDao
import com.laura.splitthebill.model.PersonDaoImpl
import com.laura.splitthebill.view.MainActivity

class PersonController(mainActivity: MainActivity) {
    private val personDaoImpl: PersonDao = PersonDaoImpl(mainActivity)

    fun insertPerson(person: Person): Int = personDaoImpl.createPerson(person)
    fun getPerson(id: Int) = personDaoImpl.readPerson(id)
    fun getPeople() = personDaoImpl.readPeople()
    fun editPerson(person: Person) = personDaoImpl.updatePerson(person)
    fun removePerson(id: Int) = personDaoImpl.deletePerson(id)
}