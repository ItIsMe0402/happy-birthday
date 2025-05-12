package com.github.itisme0402.happybirthday

import android.app.Application
import com.github.itisme0402.happybirthday.di.initKoin
import com.github.itisme0402.happybirthday.util.initNapier

class HappyBirthdayApp : Application() {
    override fun onCreate() {
        super.onCreate()

        initNapier()
        initKoin {
        }
    }
}
