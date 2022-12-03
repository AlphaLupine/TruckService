package com.lupinespace


import com.lupinespace.plugins.configureRouting
import com.lupinespace.plugins.configureSerialization
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.lupinespace.plugins.*

fun main() {

    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureSerialization()
    //configureSecurity() TODO: Add authentication
    configureRouting()
}
