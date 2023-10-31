package com.laura.splitthebill.model

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Person::class], version = 1)
abstract class PersonRoomDaoDatabase: RoomDatabase(){
    abstract fun getPersonRoomDao(): PersonRoomDao
}