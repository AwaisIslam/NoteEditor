package com.ak.noteeditor.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.ak.noteeditor.data.NoteModel
import com.ak.noteeditor.databinding.NoteListItemBinding
import com.ak.noteeditor.viewholder.NoteListViewHolder

class NoteListAdapter(
    private val inflater: LayoutInflater,
    private val onCheckBoxToggle: (NoteModel) -> Unit,
    private val onItemClick: (NoteModel) -> Unit
) : ListAdapter<NoteModel, NoteListViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        NoteListViewHolder(
            NoteListItemBinding.inflate(inflater, parent, false),
            onCheckBoxToggle,
            onItemClick
        )

    override fun onBindViewHolder(holder: NoteListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private object DiffCallback : DiffUtil.ItemCallback<NoteModel>() {
        override fun areItemsTheSame(oldItem: NoteModel, newItem: NoteModel) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: NoteModel,
            newItem: NoteModel
        ) = oldItem.isCompleted == newItem.isCompleted &&
                oldItem.description == newItem.description
    }
}