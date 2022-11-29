package lupinespace.com.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import lupinespace.com.data.models.*
import io.ktor.server.routing.*
import lupinespace.com.data.getPartialUserById
import lupinespace.com.data.models.UserAccount

fun Route.userRouting() {
    route("/get-partial-user") {
        get {
            val userId = call.receive<UserRequest>().id ?: return@get call.respondText(
                "Received no ID",
                status = HttpStatusCode.BadRequest
            )
            val user = getPartialUserById(userId) ?: return@get call.respondText(
                "No user found",
                status = HttpStatusCode.NotFound
            )
            call.respond(user)
        }
    }
}