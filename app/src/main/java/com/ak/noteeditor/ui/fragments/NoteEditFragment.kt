package com.ak.noteeditor.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.ak.noteeditor.R
import com.ak.noteeditor.databinding.FragmentNoteEditBinding
import com.ak.noteeditor.viewmodel.SingleNoteViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class NoteEditFragment : Fragment() {

    private var binding: FragmentNoteEditBinding? = null
    private val args: NoteEditFragmentArgs by navArgs()
    private val viewModel: SingleNoteViewModel by viewModel { parametersOf(args.noteId) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FragmentNoteEditBinding.inflate(inflater,container,false)
        .apply { binding = this }
        .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.getNoteModel()?.let {
            binding?.apply {
                checkBoxIsEditNoteCompleted.isChecked = it.isCompleted
                editTextNoteDesc.setText(it.description)
                editTextEditNote.setText(it.notes)
            }
        }
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }
}