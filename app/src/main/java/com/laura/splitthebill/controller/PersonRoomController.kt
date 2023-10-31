package com.laura.splitthebill.controller

import android.os.AsyncTask
import androidx.room.Room
import com.laura.splitthebill.model.Person
import com.laura.splitthebill.model.PersonRoomDao
import com.laura.splitthebill.model.PersonRoomDaoDatabase
import com.laura.splitthebill.view.MainActivity

class PersonRoomController(private val mainActivity: MainActivity) {
    private val personDaoImpl: PersonRoomDao by lazy {
        Room.databaseBuilder(
            mainActivity,
            PersonRoomDaoDatabase::class.java,
            PersonRoomDao.PEOPLE_DATABASE_FILE
        ).build().getPersonRoomDao()
    }

    fun insertPerson(person: Person) {
        Thread {
            personDaoImpl.createPerson(person)
            getPeople()
        }.start()
    }

    fun getPerson(id: Int) = personDaoImpl.readPerson(id)

    fun getPeople() {
        object: AsyncTask<Unit, Unit, MutableList<Person>>(){
            override fun doInBackground(vararg params: Unit?): MutableList<Person> {
                return personDaoImpl.readPerson()
            }

            override fun onPostExecute(result: MutableList<Person>?) {
                super.onPostExecute(result)
                result?.also {
                    mainActivity.updatePeopleList(result)
                }
            }
        }.execute()
    }

    fun editPerson(person: Person){
        Thread {
            personDaoImpl.updatePerson(person)
            getPeople()
        }.start()
    }

    fun removePerson(person: Person){
        Thread {
            personDaoImpl.deletePerson(person)
            getPeople()
        }.start()
    }
}