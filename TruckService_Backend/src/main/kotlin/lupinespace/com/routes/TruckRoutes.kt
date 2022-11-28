package lupinespace.com.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import lupinespace.com.models.*
import io.ktor.server.routing.*

fun Route.truckRouting() {
    route("/trucks") {
        get {
            if(truckStorage.isNotEmpty()) {
                call.respond(truckStorage)
            } else {
                call.respondText ( "No trucks present", status = HttpStatusCode.OK )
            }
        }
        get("{vrm?}") {
            val vrm = call.parameters["vrm"] ?: return@get call.respondText(
                "Missing VRM",
                status = HttpStatusCode.BadRequest
            )
            val truck = truckStorage.find{ it.vrm == vrm } ?: return@get call.respondText(
                "Unable to find truck with this vrm",
                status = HttpStatusCode.NotFound
            )
            call.respond(truck)
        } //TODO: Fix this, supplying a vrm as a parameter in the URL will ALWAYS return ALL trucks in the list
        post {
            val truck = call.receive<Truck>()
            truckStorage.add(truck)
            call.respondText("Successfully stored truck", status = HttpStatusCode.Created)
        }
        delete("{vrm?}") {
            val vrm = call.parameters["vrm"] ?: return@delete call.respond(HttpStatusCode.BadRequest)
            if(truckStorage.removeIf { it.vrm == vrm }) {
                call.respondText("Truck removed successfully", status = HttpStatusCode.Accepted)
            } else {
                call.respondText("Not Found", status = HttpStatusCode.NotFound)
            }
        }
    }
}