package lupinespace.com.data.models

import kotlinx.serialization.Serializable

@Serializable
data class Truck(
    val vrm: String,
    val model: String
)

val truckStorage = mutableListOf<Truck>() //TODO: Implement a database