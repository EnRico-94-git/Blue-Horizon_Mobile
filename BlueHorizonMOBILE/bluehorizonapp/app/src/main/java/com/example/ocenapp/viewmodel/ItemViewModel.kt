package com.example.ocenapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ocenapp.model.Item

class ItemViewModel : ViewModel() {
    private val _items = MutableLiveData<List<Item>>()
    val items: LiveData<List<Item>> get() = _items

    init {
        loadItems()
    }

    fun loadItems() {
        // Inicialize a lista de itens com os exemplos
        val exampleItems = listOf(
            Item("1", "Área A", 70, 25.5f, 0.8f),
            Item("2", "Área B", 40, 22.3f, 0.9f),
            Item("3", "Área C", 90, 28.1f, 0.7f),
            Item("4", "Área D", 30, 24.0f, 1.0f)
        )
        _items.value = exampleItems
    }

    fun getItem(itemId: String): LiveData<Item?> {
        val item = _items.value?.find { it.id == itemId }
        val itemLiveData = MutableLiveData<Item?>()
        itemLiveData.value = item
        return itemLiveData
    }

    fun addItem(item: Item) {
        val currentItems = _items.value.orEmpty().toMutableList()
        currentItems.add(item)
        _items.value = currentItems.toList()
    }

    fun updateItem(updatedItem: Item) {
        val currentItems = _items.value.orEmpty().toMutableList()
        val index = currentItems.indexOfFirst { it.id == updatedItem.id }
        if (index != -1) {
            currentItems[index] = updatedItem
            _items.value = currentItems.toList()
        }
    }

    fun save(item: Item) {
        val currentItems = _items.value.orEmpty().toMutableList()
        val index = currentItems.indexOfFirst { it.id == item.id }
        if (index != -1) {
            currentItems[index] = item
        } else {
            currentItems.add(item)
        }
        _items.value = currentItems.toList()
    }

    fun deleteItem(itemId: String) {
        val currentItems = _items.value.orEmpty().toMutableList()
        currentItems.removeAll { it.id == itemId }
        _items.value = currentItems
    }
}