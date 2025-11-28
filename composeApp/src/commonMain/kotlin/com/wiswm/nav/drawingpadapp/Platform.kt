package com.wiswm.nav.drawingpadapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform