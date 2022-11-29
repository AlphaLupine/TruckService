package lupinespace.com.plugins

import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import lupinespace.com.routes.truckRouting
import lupinespace.com.routes.userRouting

fun Application.configureRouting() {

    routing {
        truckRouting()
        userRouting()
    }
}
