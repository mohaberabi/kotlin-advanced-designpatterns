package org.example.paradigms.coroutines.how_they_work


import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlin.system.measureTimeMillis


class Async {


    companion object {
        private val scope = CoroutineScope(Dispatchers.Default)
        suspend fun profile(id: String): UserProfile {
            val bio = fetchBioHttp(id)
            val friends = fetchFriendsFromDb(id)
            val pics = fetchPicsFromDb(id)
            return UserProfile(bio = bio.await(), pics = pics.await(), friends = friends.await(), id = id)
        }

        private fun fetchFriendsFromDb(
            id: String,
        ) = scope.async {
            delay(500)
            emptyList<String>()
        }

        private fun fetchPicsFromDb(
            id: String,
        ) = scope.async {
            delay(100)
            emptyList<String>()
        }


        private fun fetchBioHttp(
            id: String,
        ) = scope.async {
            delay(1000)
            "nio"
        }

    }


}

private suspend fun main() {
    val time = measureTimeMillis {
        Async.profile("id")

    }
    println(time)

}