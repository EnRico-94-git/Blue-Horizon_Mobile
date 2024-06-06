package com.example.ocenapp

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.ocenapp.databinding.ActivityDetailBinding
import com.example.ocenapp.model.Item
import com.example.ocenapp.viewmodel.ItemViewModel
import java.util.UUID

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val itemViewModel: ItemViewModel by viewModels()
    private var itemId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        itemId = intent.getStringExtra("item_id")
        if (itemId != null) {
            itemViewModel.getItem(itemId!!).observe(this) { item ->
                if (item != null) {
                    // Atualiza os campos com os dados do item
                    binding.editTextName.setText(item.name)
                    binding.editTextPollutionLevel.setText(item.pollutionLevel.toString())
                    binding.editTextTemperature.setText(item.temperature.toString())
                    binding.editTextBiodiversityIndex.setText(item.biodiversityIndex.toString())
                }
            }
        }

        binding.buttonSave.setOnClickListener {
            // Obtém os dados dos campos de entrada
            val name = binding.editTextName.text.toString()
            val pollutionLevel = binding.editTextPollutionLevel.text.toString().toIntOrNull() ?: 0
            val temperature = binding.editTextTemperature.text.toString().toFloatOrNull() ?: 0f
            val biodiversityIndex = binding.editTextBiodiversityIndex.text.toString().toFloatOrNull() ?: 0f

            // Cria um novo item com os dados inseridos
            val newItem = Item(
                id = itemId ?: UUID.randomUUID().toString(),
                name = name,
                pollutionLevel = pollutionLevel,
                temperature = temperature,
                biodiversityIndex = biodiversityIndex
            )

            // Verifica se é uma adição ou uma atualização
            if (itemId == null) {
                itemViewModel.addItem(newItem)
            } else {
                itemViewModel.updateItem(newItem)
            }

            // Fecha a atividade
            finish()
        }
    }
}