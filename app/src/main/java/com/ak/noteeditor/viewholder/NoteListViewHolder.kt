package com.ak.noteeditor.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.ak.noteeditor.data.NoteEditorModel
import com.ak.noteeditor.databinding.NoteListItemBinding

class NoteListViewHolder(
    private val binding: NoteListItemBinding,
    val onCheckBoxToggle: (NoteEditorModel) -> Unit,
    val onItemClick: (NoteEditorModel) -> Unit
): RecyclerView.ViewHolder(binding.root) {

    fun bind(model: NoteEditorModel) {
        binding.apply {
            root.setOnClickListener { onItemClick(model) }
            checkBoxIsCompleted.isChecked = model.isCompleted
            checkBoxIsCompleted.setOnCheckedChangeListener{ _, _-> onCheckBoxToggle(model)}
            textViewDescription.text = model.description
        }
    }
}