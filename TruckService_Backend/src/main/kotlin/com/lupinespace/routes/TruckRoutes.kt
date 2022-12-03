package com.lupinespace.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import com.lupinespace.data.createOrUpdateTruckById
import com.lupinespace.data.getTrucks
import com.lupinespace.data.models.Truck

fun Route.truckRouting() {
    route("/get-trucks") {
        get {
            val trucks = getTrucks()
            if(trucks.isEmpty()) return@get call.respondText(
                "No trucks found",
                status = HttpStatusCode.OK
            )
            call.respond(trucks)
        }
    }
    route("/create-or-update-truck") {
        post {
            val truck = call.receive<Truck>()
            val operation = createOrUpdateTruckById(truck)
            if(operation) return@post call.respondText(
                "Successfully created/updated truck",
                status = HttpStatusCode.Created
            ) else {
                return@post call.respondText(
                    "Unable to create/update truck",
                    status = HttpStatusCode.ExpectationFailed
                )
            }
        }
    }
}