package lupinespace.com.plugins

import io.ktor.server.routing.*
import io.ktor.server.application.*
import lupinespace.com.routes.truckRouting
import lupinespace.com.routes.userRouting

fun Application.configureRouting() {

    routing {
        truckRouting()
        userRouting()
    }
}
