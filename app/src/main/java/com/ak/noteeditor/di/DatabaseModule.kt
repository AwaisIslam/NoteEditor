package com.ak.noteeditor.di

import com.ak.noteeditor.database.NoteDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

object DatabaseModule {

    private val databaseModules = module {
        single { NoteDatabase(androidContext()) }
    }

    private val daoModule = module {
        single { get<NoteDatabase>().notesDAO() }
    }

    val modules = listOf(databaseModules, daoModule)
}