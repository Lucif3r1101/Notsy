package com.plcoding.cleanarchitecturenoteapp.di

import androidx.room.Room
import androidx.room.RoomDatabase
import com.plcoding.cleanarchitecturenoteapp.feature_note.data.data_source.NoteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import android.app.Application
import android.provider.ContactsContract
import com.plcoding.cleanarchitecturenoteapp.feature_note.data.repository.NoteRepositoryImpl
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.repository.NoteRepository
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.use_case.*

//one module per feature

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application): NoteDatabase {
        return Room.databaseBuilder(
            app,
            NoteDatabase::class.java,
            NoteDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    //db instance tp retreive the correpsoinding dao // injectind dependieces
    fun provideRepository(db: NoteDatabase): NoteRepository
    {
        return NoteRepositoryImpl(db.noteDao)
    }

    //Note use cases
    @Provides
    @Singleton
    fun provideNoteUseCases(repository: NoteRepository): NotesUseCases{
        return NotesUseCases(
            getNotes = GetNotes(repository),
            deleteNotes = DeleteNotes(repository),
            addNote = AddNote(repository),
            getNote = GetNote(repository)
        )
    }
}