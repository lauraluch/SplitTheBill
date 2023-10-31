package com.laura.splitthebill.model

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
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
        private const val THINGS_BOUGHT_COLUMN = "things"

        private const val CREATE_PERSON_TABLE_STATEMENT =
            "CREATE TABLE IF NOT EXISTS $PERSON_TABLE (" +
                    "$ID_COLUMN INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "$NAME_COLUMN TEXT NOT NULL, " +
                    "$TOTAL_PRICE_COLUMN REAL NOT NULL, " +
                    "$THINGS_BOUGHT_COLUMN TEXT NOT NULL );"
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

    override fun createPerson(person: Person) = personSQLiteDatabase.insert(
        PERSON_TABLE,
        null,
        person.toContentValues()
    ).toInt()

    override fun readPerson(id: Int): Person? {
        val cursor = personSQLiteDatabase.rawQuery(
            "SELECT * FROM $PERSON_TABLE WHERE $ID_COLUMN = ?",
            arrayOf(id.toString())
        )

        val person = if(cursor.moveToFirst()) cursor.rowToPerson() else null
        cursor.close()
        return person
    }

    override fun readPeople(): MutableList<Person> {
        val peopleList = mutableListOf<Person>()

        val cursor = personSQLiteDatabase.rawQuery(
            "SELECT * FROM $PERSON_TABLE ORDER BY $NAME_COLUMN",
            null
        )

        while (cursor.moveToNext()) {
            peopleList.add(cursor.rowToPerson())
        }
        cursor.close()

        return peopleList
    }

    override fun updatePerson(person: Person): Int = personSQLiteDatabase.update(
        PERSON_TABLE,
        person.toContentValues(),
        "$ID_COLUMN = ?",
        arrayOf(person.id.toString())
    )

    override fun deletePerson(id: Int): Int = personSQLiteDatabase.delete(
        PERSON_TABLE,
        "$ID_COLUMN = ?",
        arrayOf(id.toString())
    )

    private fun Cursor.rowToPerson(): Person = Person(
        getInt(getColumnIndexOrThrow(ID_COLUMN)),
        getString(getColumnIndexOrThrow(NAME_COLUMN)),
        getDouble(getColumnIndexOrThrow(TOTAL_PRICE_COLUMN)),
        getString(getColumnIndexOrThrow(THINGS_BOUGHT_COLUMN))
    )

    private fun Person.toContentValues(): ContentValues = with(ContentValues()) {
        put(NAME_COLUMN, name)
        put(TOTAL_PRICE_COLUMN, totalPricePaid)
        put(THINGS_BOUGHT_COLUMN, thingsBought)
        this
    }
}