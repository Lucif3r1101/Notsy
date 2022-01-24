package com.plcoding.cleanarchitecturenoteapp.feature_note.data.data_source

import androidx.compose.runtime.rememberCompositionContext
import androidx.room.*
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.model.Note
import kotlinx.coroutines.flow.Flow


@Dao
interface NoteDao {
    
    @Query("Select * from note")
    fun getNotes(): Flow<List<Note>>
    
    @Query("Select * from note where id = :id")
    suspend fun getNoteById(id: Int): Note?

    @Insert(onConflict = OnConflictStrategy.REPLACE)  // Replace helps in updating the note with an existing id
    suspend fun insertNode(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)
}