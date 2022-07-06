package com.ak.noteeditor.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ak.noteeditor.R
import com.ak.noteeditor.adapter.NoteListAdapter
import com.ak.noteeditor.data.NoteEditorModel
import com.ak.noteeditor.databinding.FragmentNoteslistBinding
import com.ak.noteeditor.databinding.NoteListItemBinding
import com.ak.noteeditor.viewmodel.NoteListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class NotesListFragment : Fragment() {

    private val noteViewModel: NoteListViewModel by viewModel()

    private var binding: FragmentNoteslistBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentNoteslistBinding.inflate(inflater, container, false)
        .also { binding = it }
        .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = NoteListAdapter(
            layoutInflater,
            onCheckBoxToggle = { noteViewModel.save(it.copy(isCompleted = !it.isCompleted))},
            onItemClick = ::showFragmentDetails)

        binding?.itemRecyclerview?.apply {
            setAdapter(adapter)
            layoutManager = LinearLayoutManager(context)

            addItemDecoration(
                DividerItemDecoration(activity,DividerItemDecoration.VERTICAL)
            )
        }

        adapter.submitList(noteViewModel.getNoteItems())
        binding?.textView?.visibility = View.GONE
    }

    private fun showFragmentDetails(model: NoteEditorModel) {
        findNavController().navigate(NotesListFragmentDirections.actionNotesListFragmentToNotesDetailFragment(model.id))
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }
}