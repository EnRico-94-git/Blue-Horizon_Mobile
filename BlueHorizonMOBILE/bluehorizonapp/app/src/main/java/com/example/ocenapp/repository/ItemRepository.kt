package com.example.ocenapp.repository

import com.example.ocenapp.model.Item

class ItemRepository(private val firebaseService: FirebaseService) {

    fun addItem(item: Item, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        firebaseService.addItem(item, onSuccess, onFailure)
    }

    fun getItems(onSuccess: (List<Item>) -> Unit, onFailure: (Exception) -> Unit) {
        firebaseService.getItems(onSuccess, onFailure)
    }

    fun updateItem(id: String, item: Item, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        firebaseService.updateItem(id, item, onSuccess, onFailure)
    }

    fun deleteItem(id: String, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        firebaseService.deleteItem(id, onSuccess, onFailure)
    }
}