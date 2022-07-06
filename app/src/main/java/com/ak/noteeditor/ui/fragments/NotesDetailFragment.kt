package com.ak.noteeditor.ui.fragments

import android.os.Bundle
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.ak.noteeditor.databinding.FragmentNotesDetailBinding
import com.ak.noteeditor.viewmodel.SingleNoteViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class NotesDetailFragment : Fragment() {

    private val args: NotesDetailFragmentArgs by navArgs()

    private var binding: FragmentNotesDetailBinding? = null

    private val viewModel: SingleNoteViewModel by viewModel { parametersOf(args.noteId) }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FragmentNotesDetailBinding.inflate(layoutInflater, container, false)
        .apply { binding = this }
        .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.getNoteModel()?.let {
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

    override fun onDestroy() {
        binding = null

        super.onDestroy()
    }
}