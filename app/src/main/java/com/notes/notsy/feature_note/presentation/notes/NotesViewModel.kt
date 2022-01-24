package com.plcoding.cleanarchitecturenoteapp.feature_note.presentation.notes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.model.Note
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.use_case.NotesUseCases
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.util.NoteOrder
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.util.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


//help in creating ui
@HiltViewModel
class NotesViewModel @Inject constructor(

    private val notesUseCases: NotesUseCases
) : ViewModel() {


    private val _state= mutableStateOf(NotesState())
    //private var recentlyDeletedNotes: Note? = null]
    private var recentlyDeletedNotes: Note? = null
  /*wrong impport*/
    val state: State<NotesState> = _state

    //private var getNotesJob: Job? = null //nullable job
    private var getNotesJob: Job? = null

    init {
        getNotes(NoteOrder.Date(OrderType.Descending))
    }

    //one state object that rep current ui state of note screen
    // to trigger from ui

    // take cases from use-cases

    fun onEvent(event: NotesEvent){
        when(event){
            is NotesEvent.Order -> {
                //check class instead of references
                if(state.value.noteOrder::class == event.noteOrder::class &&
                        state.value.noteOrder.orderType == event.noteOrder.orderType
                ){
                    return
                }
                getNotes(event.noteOrder)
            }
            is NotesEvent.DeleteNote -> {
                viewModelScope.launch {
                    notesUseCases.deleteNotes(event.note)  //operator fun that why we can call it as a func
                    recentlyDeletedNotes = event.note
                }
            }
            is NotesEvent.RestoreNote -> {
                viewModelScope.launch {
                    notesUseCases.addNote(recentlyDeletedNotes ?: return@launch) //if null
                    recentlyDeletedNotes = null
                }
            }

            // take form noteState
            is NotesEvent.ToggleOrderSection -> {
                _state.value = state.value.copy(
                    isOrderSelectionVisible = !state.value.isOrderSelectionVisible
                )
            }
        }
    }

    private fun getNotes(noteOrder: NoteOrder){
        //cancelling the old co-rotuine accessing the db
        getNotesJob?.cancel()
        getNotesJob = notesUseCases.getNotes(noteOrder)
            .onEach { notes ->
                _state.value = state.value.copy(
                    notes =  notes,
                    noteOrder = noteOrder
                )
            }
            .launchIn(viewModelScope) //co-routines
    }
}