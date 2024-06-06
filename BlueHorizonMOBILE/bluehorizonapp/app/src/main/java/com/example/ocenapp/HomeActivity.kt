package com.example.ocenapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ocenapp.databinding.ActivityHomeBinding
import com.example.ocenapp.viewmodel.ItemViewModel

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val itemViewModel: ItemViewModel by viewModels()
    private lateinit var itemAdapter: ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        observeViewModel()

        binding.fabAddItem.setOnClickListener {
            startDetailActivityForAdding()
        }
    }

    private fun setupRecyclerView() {
        itemAdapter = ItemAdapter(
            viewModel = itemViewModel,
            lifecycleOwner = this,
            onItemClicked = { item ->
                startDetailActivityForEditing(item.id)
            },
            onItemDeleteClicked = { item ->
                itemViewModel.deleteItem(item.id)
            }
        )
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@HomeActivity)
            adapter = itemAdapter
        }
    }

    private fun observeViewModel() {
        itemViewModel.items.observe(this) { items ->
            itemAdapter.submitList(items)
        }
    }

    private val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            // Verifique se a ação foi bem-sucedida e recarregue a lista apenas se necessário
            val data = result.data
            data?.let {
                val itemId = it.getStringExtra("item_id")
                if (!itemId.isNullOrEmpty()) {
                    // Um item foi adicionado ou editado, atualize a lista apenas nesse caso
                    itemViewModel.loadItems()
                }
            }
        }
    }

    private fun startDetailActivityForAdding() {
        val intent = Intent(this, DetailActivity::class.java)
        startForResult.launch(intent)
    }

    private fun startDetailActivityForEditing(itemId: String) {
        val intent = Intent(this, DetailActivity::class.java).apply {
            putExtra("item_id", itemId)
        }
        startForResult.launch(intent)
    }
}