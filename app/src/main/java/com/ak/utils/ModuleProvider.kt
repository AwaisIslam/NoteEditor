package com.ak.utils

import com.ak.noteeditor.di.DatabaseModule
import com.ak.noteeditor.di.RepositoryModule
import com.ak.noteeditor.di.ViewModelModule
import org.koin.core.module.Module

val AppModules:List<Module> by lazy {
    mutableListOf<Module>().apply {
        addAll(DatabaseModule.modules)
        addAll(RepositoryModule.modules)
        addAll(ViewModelModule.modules)
    }
}