package lupinespace.com.data.models

import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

@Serializable
data class Truck(
    @BsonId
    var id: String = ObjectId().toString(),
    val vrm: String,
    val model: String
)