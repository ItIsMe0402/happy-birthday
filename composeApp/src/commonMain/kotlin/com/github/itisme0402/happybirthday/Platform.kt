package com.github.itisme0402.happybirthday

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform