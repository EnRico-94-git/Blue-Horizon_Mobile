package com.example.ocenapp.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.example.ocenapp.model.Item

class FirebaseService {
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    // Adiciona um item ao Firestore
    fun addItem(item: Item, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        db.collection("items")
            .add(item)
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener { e ->
                onFailure(e)
            }
    }

    // Obtém todos os itens do Firestore
    fun getItems(onSuccess: (List<Item>) -> Unit, onFailure: (Exception) -> Unit) {
        db.collection("items")
            .get()
            .addOnSuccessListener { result ->
                val items: List<Item> = result.map { document ->
                    document.toObject(Item::class.java)
                }
                onSuccess(items)
            }
            .addOnFailureListener { e ->
                onFailure(e)
            }
    }

    // Atualiza um item no Firestore
    fun updateItem(id: String, item: Item, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        db.collection("items").document(id)
            .set(item)
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener { e ->
                onFailure(e)
            }
    }

    // Exclui um item do Firestore
    fun deleteItem(id: String, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        db.collection("items").document(id)
            .delete()
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener { e ->
                onFailure(e)
            }
    }
}