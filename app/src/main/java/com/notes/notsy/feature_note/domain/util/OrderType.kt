package com.plcoding.cleanarchitecturenoteapp.feature_note.domain.util


// ascending or descending

sealed class OrderType{
    object Ascending: OrderType()
    object Descending: OrderType()
}
