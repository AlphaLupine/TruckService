package lupinespace.com.data.models

import kotlinx.serialization.Serializable

@Serializable
data class AuthUser(
    val username: String,
    val role: String,
    val authToken: String
)
