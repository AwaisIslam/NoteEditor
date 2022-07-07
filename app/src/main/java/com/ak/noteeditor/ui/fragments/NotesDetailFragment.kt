package com.ak.noteeditor.ui.fragments

import android.database.DatabaseUtils
import android.os.Bundle
import android.text.format.DateUtils
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ak.noteeditor.R
import com.ak.noteeditor.databinding.FragmentNotesDetailBinding
import com.ak.noteeditor.viewmodel.SingleNoteViewModel
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class NotesDetailFragment : Fragment() {

    private val args: NotesDetailFragmentArgs by navArgs()

    private var binding: FragmentNotesDetailBinding? = null

    private val viewModel: SingleNoteViewModel by viewModel { parametersOf(args.noteId) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FragmentNotesDetailBinding.inflate(layoutInflater, container, false)
        .apply { binding = this }
        .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.states.collect{ state->
                state.item?.let {
                    binding?.apply {
                        imageViewCompleted.visibility = if (it.isCompleted) View.VISIBLE else View.GONE
                        textViewNoteTitle.text = it.description
                        textViewNoteCreatedDate.text = DateUtils.getRelativeDateTimeString(
                            requireContext(),
                            it.createdOn.toEpochMilli(),
                            DateUtils.MINUTE_IN_MILLIS,
                            DateUtils.WEEK_IN_MILLIS,
                            0
                        )
                        textViewNoteDetail.text = it.notes
                    }
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.note_details_menu, menu)

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_edit -> {
                showEditFragment()
                return true
            }

        }

        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        binding = null

        super.onDestroy()
    }

    private fun showEditFragment() {
        findNavController().navigate(
            NotesDetailFragmentDirections.actionNotesDetailFragmentToNoteEditFragment(args.noteId)
        )
    }
}