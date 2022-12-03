package lupinespace.com.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import lupinespace.com.data.createOrUpdateUserById
import lupinespace.com.data.getPartialUserById
import lupinespace.com.data.models.UserAccount
import lupinespace.com.data.models.receivables.UserRequest

fun Route.userRouting() {
    route("/get-partial-user") {
        get {
            val userId = call.receive<UserRequest>().id ?: return@get call.respondText(
                "Received no ID",
                status = HttpStatusCode.BadRequest
            )
            val user = getPartialUserById(userId) ?: return@get call.respondText(
                "No user found",
                status = HttpStatusCode.OK
            )
            call.respond(user)
        }
    }

    route("/create-or-update-user") {
        post {
            val user = call.receive<UserAccount>()
            val operation = createOrUpdateUserById(user)
            if(operation) return@post call.respondText(
                "Successfully created/updated user",
                status = HttpStatusCode.Created
            ) else {
                return@post call.respondText(
                    "Unable to create/update user",
                    status = HttpStatusCode.ExpectationFailed
                )
            }
        }
    }
}