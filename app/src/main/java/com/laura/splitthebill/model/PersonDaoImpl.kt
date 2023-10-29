package com.laura.splitthebill.model

import android.content.Context

class PersonDaoImpl(context: Context) : PersonDao {
    companion object Constant {
        private const val PEOPLE_DATABASE_FILE = "people"
        private const val PERSON_TABLE = "person"
        private const val ID_COLUMN = "id"
        private const val NAME_COLUMN = "name"
        private const val TOTAL_PRICE_COLUMN = "total"

        private const val ITEM_TABLE = "item"
        private const val PRICE_COLUMN = "price"

        private const val PERSON_ITEMS_TABLE = "person_items"
        private const val ID_PERSON_COLUMN = "person_id"
        private const val NAME_PERSON_COLUMN = "person_name"
        private const val ID_ITEM_COLUMN = "item_id"
        private const val NAME_ITEM_COLUMN = "item_name"

        private const val CREATE_PERSON_TABLE_STATEMENT =
            "CREATE TABLE IF NOT EXISTS $PERSON_TABLE (" +
                    "$ID_COLUMN INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "$NAME_COLUMN TEXT NOT NULL, " +
                    "$TOTAL_PRICE_COLUMN REAL );"

        private const val CREATE_ITEM_TABLE_STATEMENT =
            "CREATE TABLE IF NOT EXISTS $ITEM_TABLE (" +
                    "$ID_COLUMN INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "$NAME_COLUMN TEXT NOT NULL, " +
                    "$PRICE_COLUMN REAL NOT NULL);"

        private const val CREATE_PERSON_ITEM_TABLE_STATEMENT =
            "CREATE TABLE IF NOT EXISTS $PERSON_ITEMS_TABLE (" +
                    "$ID_COLUMN INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "$NAME_COLUMN INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "$NAME_COLUMN TEXT NOT NULL );"
    }

    override fun createPerson(person: Person): Int {
        TODO("Not yet implemented")
    }

    override fun readPerson(id: Int): Person? {
        TODO("Not yet implemented")
    }

    override fun readPeople(): MutableList<Person> {
        TODO("Not yet implemented")
    }

    override fun updatePerson(person: Person): Int {
        TODO("Not yet implemented")
    }

    override fun deletePerson(id: Int): Int {
        TODO("Not yet implemented")
    }
}