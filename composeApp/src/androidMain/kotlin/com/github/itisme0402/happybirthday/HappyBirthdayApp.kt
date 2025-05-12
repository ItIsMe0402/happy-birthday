package com.github.itisme0402.happybirthday

import android.app.Application

class HappyBirthdayApp : Application() {
    override fun onCreate() {
        super.onCreate()

        initNapier()
        initKoin {
        }
    }
}
