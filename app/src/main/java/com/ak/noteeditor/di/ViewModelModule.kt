package com.ak.noteeditor.di

import com.ak.noteeditor.viewmodel.NoteListViewModel
import com.ak.noteeditor.viewmodel.SingleNoteViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object ViewModelModule {

    private val viewModelModules = module {
        viewModel{ NoteListViewModel(get()) }
        viewModel{ (noteId: String) -> SingleNoteViewModel(get(), noteId) }
    }

    val modules = listOf(viewModelModules)
}