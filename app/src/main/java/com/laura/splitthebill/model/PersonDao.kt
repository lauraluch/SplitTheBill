package com.laura.splitthebill.model

interface PersonDao {
    fun createPerson(person: Person): Int
    fun readPerson(id: Int): Person?
    fun readPeople(): MutableList<Person>
    fun updatePerson(person: Person): Int
    fun deletePerson(id: Int): Int
}