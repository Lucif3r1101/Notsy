package com.plcoding.cleanarchitecturenoteapp.feature_note.presentation.notes.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.unit.dp
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.model.Note
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.util.NoteOrder
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.util.OrderType


@Composable
fun OrderSection(
    modifier: Modifier = Modifier,  //subject to error
    noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    onOrderChange: (NoteOrder) -> Unit
){
    Column(
        modifier = modifier
    ) {
       Row (
           modifier = Modifier.fillMaxWidth()
       ) {
           DefaultRadioButton(
               text = "Title",
               selected = noteOrder is NoteOrder.Title,
               onSelect = { onOrderChange(NoteOrder.Title(noteOrder.orderType)) })

           Spacer(modifier = Modifier.width(8.dp))
           DefaultRadioButton(
<<<<<<< HEAD
               text = "Date",
=======
               text = "By Date",
>>>>>>> 0ffdb67c620d355fd3f6abc52c3852d9762de8c6
               selected = noteOrder is NoteOrder.Date,
               onSelect = { onOrderChange(NoteOrder.Date(noteOrder.orderType)) })

           Spacer(modifier = Modifier.width(8.dp))
           DefaultRadioButton(
               text = "Color",
               selected = noteOrder is NoteOrder.Color,
               onSelect = { onOrderChange(NoteOrder.Color(noteOrder.orderType)) })
       }
    }
    Spacer(modifier = Modifier.width(16.dp))
    Row(
        modifier = Modifier.fillMaxWidth()
    )
    {
        DefaultRadioButton(
            text = "Ascending",
            selected = noteOrder.orderType is OrderType.Ascending,
            onSelect = {
                onOrderChange(noteOrder.copy(OrderType.Ascending)) })

        Spacer(modifier = Modifier.width(8.dp))
        DefaultRadioButton(
            text = "Descending",
            selected = noteOrder.orderType is OrderType.Descending,
            onSelect = {
                onOrderChange(noteOrder.copy(OrderType.Descending)) })

    }

}