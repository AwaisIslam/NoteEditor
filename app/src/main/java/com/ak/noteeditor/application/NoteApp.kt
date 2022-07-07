package com.ak.noteeditor.application

import android.app.Application
import com.ak.noteeditor.repository.NoteRepository
import com.ak.noteeditor.viewmodel.NoteListViewModel
import com.ak.noteeditor.viewmodel.SingleNoteViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class NoteApp: Application() {

    private val koinModule = module {
        //single { NoteDatabase.createDatabase(androidContext()) }
        single { NoteRepository() }
        viewModel{ NoteListViewModel(get()) }
        viewModel{ (noteId: String) -> SingleNoteViewModel(get(), noteId) }
    }

    override fun onCreate() {
        super.onCreate()

        /**
         * modules(koinModule)
         * modules do not work due to some version conflict instead use
         * koin.loadModules(listOf(module))
         */
        startKoin {
            androidLogger()
            androidContext(this@NoteApp)
            koin.loadModules(listOf(koinModule))
        }
    }
}