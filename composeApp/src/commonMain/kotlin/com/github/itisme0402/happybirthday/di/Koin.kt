package com.github.itisme0402.happybirthday.di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.includes

fun initKoin(config : KoinAppDeclaration? = null) {
    startKoin {
        printLogger()
        includes(config)
        modules(
            networkModule,
            uiModule,
        )
    }
}
