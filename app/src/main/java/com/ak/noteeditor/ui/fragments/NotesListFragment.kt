package com.ak.noteeditor.ui.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ak.noteeditor.R
import com.ak.noteeditor.adapter.NoteListAdapter
import com.ak.noteeditor.data.NoteModel
import com.ak.noteeditor.databinding.FragmentNoteslistBinding
import com.ak.noteeditor.viewmodel.NoteListViewModel
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel

class NotesListFragment : Fragment() {

    private val noteViewModel: NoteListViewModel by viewModel()

    private var binding: FragmentNoteslistBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
    }

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

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            noteViewModel.states.collect{ state->
                adapter.submitList(state.items)

                binding?.apply {
                    when{
                        state.items.isEmpty() -> {
                            textViewPlaceholder.visibility = View.VISIBLE
                            textViewPlaceholder.setText(R.string.msg_empty)
                        }
                        else -> textViewPlaceholder.visibility = View.GONE
                    }
                }
            }
        }

        binding?.textViewPlaceholder?.visibility = View.GONE
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.new_note_menu,menu)

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_add_new_note -> {
                addNotes()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun addNotes() {
        findNavController().navigate(NotesListFragmentDirections.actionNotesListFragmentToNoteEditFragment(null))
    }

    private fun showFragmentDetails(model: NoteModel) {
        findNavController().navigate(NotesListFragmentDirections.actionNotesListFragmentToNotesDetailFragment(model.id))
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }
}