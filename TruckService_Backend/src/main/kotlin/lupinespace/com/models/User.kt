package lupinespace.com.models

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val username: String,
    val role: String,
    val authToken: String
)
