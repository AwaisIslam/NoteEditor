package com.ak.noteeditor.application

import android.app.Application
import com.ak.noteeditor.database.NoteDatabase
import com.ak.noteeditor.repository.NoteEditorRepository
import com.ak.noteeditor.viewmodel.NoteListViewModel
import com.ak.noteeditor.viewmodel.SingleNoteViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module

class NoteApp: Application() {

    private val koinModule = module {
        single {
            NoteEditorRepository(
                get<NoteDatabase>().notesDAO(),
                get(named("appScope"))
            )
        }
        viewModel{ NoteListViewModel(get()) }
        viewModel{ (noteId: String) -> SingleNoteViewModel(get(), noteId) }
        single { NoteDatabase.newInstance(androidContext()) }
        single(named("appScope")) { CoroutineScope(SupervisorJob()) }
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