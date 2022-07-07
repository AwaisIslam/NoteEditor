package com.ak.noteeditor.di

import com.ak.noteeditor.database.NoteDatabase
import com.ak.noteeditor.repository.NoteEditorRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.core.qualifier.named
import org.koin.dsl.module

object RepositoryModule {

    private val repositoryModules = module {
        single(named("appScope")) { CoroutineScope(SupervisorJob()) }
        single {
            NoteEditorRepository(
                get<NoteDatabase>().notesDAO(),
                get(named("appScope"))
            )
        }
    }

    val modules = listOf(repositoryModules)
}