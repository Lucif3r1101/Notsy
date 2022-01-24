package com.plcoding.cleanarchitecturenoteapp.feature_note.data.data_source

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.model.Note

//Database class


@Database(
    entities = [Note::class], //Define the table we have in database
    version = 1
)

abstract class NoteDatabase : RoomDatabase() {

    abstract val noteDao: NoteDao

    companion object{
        const val DATABASE_NAME = "notes_db"
    }
}


//Make use of database and call extensions of Dao in database
//repo directly access data sources, either database or api
//repo take this datasources and determine which one to forward to corresponding to use-cases
//us-cases shouldn't not know where is the data source