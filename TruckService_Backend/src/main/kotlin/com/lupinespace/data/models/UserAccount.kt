package com.lupinespace.data.models

import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

@Serializable
data class UserAccount(
    @BsonId
    var id: String = ObjectId().toString(),
    var username: String,
    var role: String,
    var password: String, //TODO: Hash these values
    var authToken: String?
)