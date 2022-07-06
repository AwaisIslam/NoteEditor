package com.ak.noteeditor.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.ak.noteeditor.data.NoteModel
import com.ak.noteeditor.databinding.NoteListItemBinding

class NoteListViewHolder(
    private val binding: NoteListItemBinding,
    val onCheckBoxToggle: (NoteModel) -> Unit,
    val onItemClick: (NoteModel) -> Unit
): RecyclerView.ViewHolder(binding.root) {

    fun bind(model: NoteModel) {
        binding.apply {
            root.setOnClickListener { onItemClick(model) }
            checkBoxIsCompleted.isChecked = model.isCompleted
            checkBoxIsCompleted.setOnCheckedChangeListener{ _, _-> onCheckBoxToggle(model)}
            textViewDescription.text = model.description
        }
    }
}