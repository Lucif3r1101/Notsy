package com.plcoding.cleanarchitecturenoteapp.feature_note.domain.use_case


//To reduce lot of use-cases
//class to wrap use-cases for single feature into one class to inject it that contain all class for feature into our view model constructor
//use the class into veiw model

data class NotesUseCases(
    val getNotes: GetNotes,
    val deleteNotes: DeleteNotes,

    //edeg case
    val addNote: AddNote,

    val getNote : GetNote
)
