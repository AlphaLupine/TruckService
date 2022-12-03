package com.lupinespace.routes

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import com.lupinespace.data.createOrUpdateTruckById
import com.lupinespace.data.getTrucks
import com.lupinespace.data.getUserAccountByUsername
import com.lupinespace.data.models.Truck
import com.lupinespace.data.models.receivables.AuthRequest
import java.lang.System.currentTimeMillis
import java.util.*



fun Route.authenticationRouting() {

    val secret = System.getenv("JWT_SECRET")
    val issuer = System.getenv("JWT_ISSUER")
    val audience = System.getenv("JWT_AUDIENCE")

    route("/login") {
        post {
            val user = call.receive<AuthRequest>()
            val userAccount = getUserAccountByUsername(user.username) ?: return@post call.respondText(
                "This account does not exist, did you mean to sign up instead?",
                status = HttpStatusCode.Unauthorized
            )
            if(user.password != userAccount.password) return@post call.respondText(
                "Username or password incorrect",
                status = HttpStatusCode.Unauthorized
            )

            val token = JWT.create()
                .withAudience(audience)
                .withIssuer(issuer)
                .withClaim("username", user.username)
                .withExpiresAt(Date(currentTimeMillis() + 60000))
                .sign(Algorithm.HMAC256(secret))
            call.respond(hashMapOf("token" to token))
        }
    }
}