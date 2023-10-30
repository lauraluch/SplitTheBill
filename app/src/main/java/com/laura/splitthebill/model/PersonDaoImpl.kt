package com.laura.splitthebill.model

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.laura.splitthebill.R
import java.sql.SQLException

class PersonDaoImpl(context: Context) : PersonDao {
    companion object Constant {
        private const val PEOPLE_DATABASE_FILE = "people"
        private const val PERSON_TABLE = "person"
        private const val ID_COLUMN = "id"
        private const val NAME_COLUMN = "name"
        private const val TOTAL_PRICE_COLUMN = "totalPrice"

        private const val CREATE_PERSON_TABLE_STATEMENT =
            "CREATE TABLE IF NOT EXISTS $PERSON_TABLE (" +
                    "$ID_COLUMN INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "$NAME_COLUMN TEXT NOT NULL, " +
                    "$TOTAL_PRICE_COLUMN REAL );"
    }

    private val personSQLiteDatabase: SQLiteDatabase

    init {
        personSQLiteDatabase =
            context.openOrCreateDatabase(PEOPLE_DATABASE_FILE, Context.MODE_PRIVATE, null)
        try {
            personSQLiteDatabase.execSQL(CREATE_PERSON_TABLE_STATEMENT)

        } catch(se: SQLException) {
            Log.e(context.getString(R.string.app_name), se.message.toString())
        }
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