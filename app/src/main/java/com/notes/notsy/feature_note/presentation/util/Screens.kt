package com.plcoding.cleanarchitecturenoteapp.feature_note.presentation.util

sealed class Screens(
    val route: String
){
    object NoteScreen: Screens("notes_screen")
    object AddEditNoteScreen: Screens("add_edit_note_screen")
}
