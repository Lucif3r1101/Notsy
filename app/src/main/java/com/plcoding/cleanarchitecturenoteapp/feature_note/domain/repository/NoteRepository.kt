package com.plcoding.cleanarchitecturenoteapp.feature_note.domain.repository

import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.model.Note
import kotlinx.coroutines.flow.Flow

//Make use of database and call extensions of Dao in database
//repo directly access data sources, either database or api
//repo take this data Sources and determine which one to forward to corresponding to use-cases
//us-cases shouldn't not know where is the data source


//Good for testing
//Fake version of repos


//The definition in form of interface belongs to domain layer

interface NoteRepository{

    fun getNotes() : Flow<List<Note>>     // return type Note in List

    suspend fun getNoteById(id: Int) : Note?

    suspend fun insertNote(note: Note)

    suspend fun deleteNote(note: Note)
}