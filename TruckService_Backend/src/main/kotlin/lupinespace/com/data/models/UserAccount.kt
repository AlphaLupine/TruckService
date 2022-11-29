package lupinespace.com.data.models

import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

@Serializable
data class UserAccount(
    @BsonId
    val id: String = ObjectId().toString(),
    val username: String,
    val role: String,
    val password: String, //TODO: Hash these values
    val authToken: String?
)