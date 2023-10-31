package com.laura.splitthebill.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface PersonRoomDao {

    companion object {
        const val PEOPLE_DATABASE_FILE = "people_room"
        private const val PERSON_TABLE = "person"
        private const val ID_COLUMN = "id"
        private const val NAME_COLUMN = "name"
        private const val TOTAL_PRICE_COLUMN = "totalPrice"
        private const val THINGS_BOUGHT_COLUMN = "things"

    }
    @Insert
    fun createPerson(person: Person)
    @Query("SELECT * FROM $PERSON_TABLE WHERE $ID_COLUMN = :id")
    fun readPerson(id: Int): Person?
    @Query("SELECT * FROM $PERSON_TABLE ORDER BY $NAME_COLUMN")
    fun readPerson(): MutableList<Person>
    @Update
    fun updatePerson(person: Person): Int
    @Delete
    fun deletePerson(person: Person): Int

}