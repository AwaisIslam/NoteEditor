package com.ak.noteeditor.ui.fragments

import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import androidx.core.content.getSystemService
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ak.noteeditor.R
import com.ak.noteeditor.data.NoteEditorModel
import com.ak.noteeditor.databinding.FragmentNoteEditBinding
import com.ak.noteeditor.viewmodel.SingleNoteViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class NoteEditFragment : Fragment() {

    private var binding: FragmentNoteEditBinding? = null
    private val args: NoteEditFragmentArgs by navArgs()
    private val viewModel: SingleNoteViewModel by viewModel { parametersOf(args.noteId) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
    }

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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.note_edit_menu,menu)

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_save -> {
                saveNote()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun saveNote() {
        binding?.apply {
            val model = viewModel.getNoteModel()
            val edited = model?.copy(
                description = editTextNoteDesc.text.toString(),
                isCompleted = checkBoxIsEditNoteCompleted.isChecked,
                notes = editTextEditNote.text.toString()
            ) ?: NoteEditorModel(
                description = editTextNoteDesc.text.toString(),
                isCompleted = checkBoxIsEditNoteCompleted.isChecked,
                notes = editTextEditNote.text.toString()
            )

            edited.let { viewModel.saveNote(it) }
        }
        navigateToNoteListFragment()
    }

    private fun navigateToNoteListFragment() {
        hideKeyboard()
        findNavController().popBackStack()
    }

    private fun hideKeyboard() {
        view?.let {
            val imm = context?.getSystemService<InputMethodManager>()

            imm?.hideSoftInputFromWindow(
                it.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS
            )
        }
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }
}