package com.lupinespace.data

import com.lupinespace.data.models.PartialUser
import com.lupinespace.data.models.Truck
import com.lupinespace.data.models.UserAccount
import org.bson.types.ObjectId
import org.litote.kmongo.coroutine.*
import org.litote.kmongo.reactivestreams.*

private val client = KMongo.createClient(String.format("mongodb+srv://lupines-space:%s@socialites.fezg0.mongodb.net/?retryWrites=true&w=majority", System.getenv("MONGO_PW"))).coroutine
private val database = client.getDatabase("TruckService")

private val userAccounts = database.getCollection<UserAccount>("Users")
private val trucks = database.getCollection<Truck>("Trucks")

suspend fun getPartialUserById(id: String): PartialUser? {
    val userAccount = userAccounts.findOneById(id) ?: return null
    return PartialUser(
        username = userAccount.username,
        role = userAccount.role
    )
}

suspend fun createOrUpdateUserById(user: UserAccount): Boolean {
    val doesExist = userAccounts.findOneById(user.id) != null
    return if (doesExist) {
        userAccounts.updateOneById(user.id, user).wasAcknowledged()
    } else {
        user.id = ObjectId().toString()
        userAccounts.insertOne(user).wasAcknowledged()
    }
}

suspend fun getTrucks(): List<Truck> {
    return trucks.find().toList()
}

suspend fun createOrUpdateTruckById(truck: Truck): Boolean {
    val doesExist = trucks.findOneById(truck.id) != null
    return if (doesExist) {
        trucks.updateOneById(truck.id, truck).wasAcknowledged()
    } else {
        truck.id = ObjectId().toString()
        trucks.insertOne(truck).wasAcknowledged()
    }
}

