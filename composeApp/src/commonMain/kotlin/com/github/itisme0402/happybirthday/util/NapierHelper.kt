package com.github.itisme0402.happybirthday.util

import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

fun initNapier() {
    Napier.base(DebugAntilog())
}
