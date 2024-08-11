package org.sonrohan.composemultiplatformcircuitplayground

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform