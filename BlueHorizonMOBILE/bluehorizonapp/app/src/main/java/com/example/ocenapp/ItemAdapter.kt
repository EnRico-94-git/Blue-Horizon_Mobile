package com.example.ocenapp
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.ocenapp.databinding.ItemViewBinding
import com.example.ocenapp.model.Item
import com.example.ocenapp.viewmodel.ItemViewModel

class ItemAdapter(
    private val viewModel: ItemViewModel,
    private val lifecycleOwner: LifecycleOwner,
    private val onItemClicked: (Item) -> Unit,
    private val onItemDeleteClicked: (Item) -> Unit
) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    init {
        // Observar a lista de itens do ViewModel
        viewModel.items.observe(lifecycleOwner) { newList ->
            submitList(newList)
        }
    }

    private var items: List<Item> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
        holder.itemView.setOnClickListener { onItemClicked(item) }
        holder.binding.buttonDelete.setOnClickListener { onItemDeleteClicked(item) }
    }

    override fun getItemCount(): Int = items.size

    fun submitList(itemList: List<Item>) {
        items = itemList
        notifyDataSetChanged()
    }

    class ItemViewHolder(val binding: ItemViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Item) {
            binding.textViewName.text = item.name
            binding.textViewPollutionLevel.text = "Nível de Poluição: ${item.pollutionLevel}"
            binding.textViewTemperature.text = "Temperatura: ${item.temperature}°C"
            binding.textViewBiodiversityIndex.text = "Índice de Biodiversidade: ${item.biodiversityIndex}"
        }
    }
}