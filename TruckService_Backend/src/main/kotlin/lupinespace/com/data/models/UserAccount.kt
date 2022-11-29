package lupinespace.com.data.models

import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId

@Serializable
data class UserAccount(
    val id: Int?,
    val username: String,
    val role: String,
    val password: String, //TODO: Hash these values
    val authToken: String?
)