package br.edu.scl.ifsp.ads.contatospdm.model
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.laura.splitthebill.model.Person

@Dao
interface PersonRoomDao {

    companion object {
        private const val PEOPLE_DATABASE_FILE = "people"
        private const val PERSON_TABLE = "person"
        private const val ID_COLUMN = "id"
        private const val NAME_COLUMN = "name"
        private const val TOTAL_PRICE_COLUMN = "totalPrice"
        private const val THINGS_BOUGHT_COLUMN = "things"

    }
    @Insert
    fun createContact(person: Person)
    @Query("SELECT *FROM $PERSON_TABLE WHERE $ID_COLUMN = :id")
    fun retrieveContact(id: Int): Person?
    @Query("SELECT * FROM $PERSON_TABLE ORDER BY $NAME_COLUMN")
    fun retrieveContacts(): MutableList<Person>
    @Update
    fun updateContact(person: Person): Int
    @Delete
    fun deleteContact(person: Person): Int

}