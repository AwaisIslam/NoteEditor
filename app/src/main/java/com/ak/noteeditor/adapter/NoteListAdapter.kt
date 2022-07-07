package com.ak.noteeditor.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.ak.noteeditor.data.NoteEditorModel
import com.ak.noteeditor.databinding.NoteListItemBinding
import com.ak.noteeditor.viewholder.NoteListViewHolder

class NoteListAdapter(
    private val inflater: LayoutInflater,
    private val onCheckBoxToggle: (NoteEditorModel) -> Unit,
    private val onItemClick: (NoteEditorModel) -> Unit
) : ListAdapter<NoteEditorModel, NoteListViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        NoteListViewHolder(
            NoteListItemBinding.inflate(inflater, parent, false),
            onCheckBoxToggle,
            onItemClick
        )

    override fun onBindViewHolder(holder: NoteListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private object DiffCallback : DiffUtil.ItemCallback<NoteEditorModel>() {
        override fun areItemsTheSame(oldItem: NoteEditorModel, newItem: NoteEditorModel) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: NoteEditorModel,
            newItem: NoteEditorModel
        ) = oldItem.isCompleted == newItem.isCompleted &&
                oldItem.description == newItem.description
    }
}