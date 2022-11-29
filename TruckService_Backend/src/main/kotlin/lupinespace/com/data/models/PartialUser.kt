package lupinespace.com.data.models

import kotlinx.serialization.Serializable

@Serializable
data class PartialUser(
    val username: String,
    val role: String
)
