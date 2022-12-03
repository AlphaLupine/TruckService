package com.lupinespace.plugins

import io.ktor.server.routing.*
import io.ktor.server.application.*
import com.lupinespace.routes.truckRouting
import com.lupinespace.routes.userRouting

fun Application.configureRouting() {

    routing {
        truckRouting()
        userRouting()
    }
}
