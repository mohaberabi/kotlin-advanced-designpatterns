package org.example.paradigms.coroutines.how_they_work

import kotlin.system.measureTimeMillis


data class UserProfile(
    val id: String,
    val pics: List<String>,
    val friends: List<String>,

    val bio: String
)

class Blocking {


    companion object {
        fun profile(id: String): UserProfile {
            val bio = fetchBioHttp(id)
            val friends = fetchFriendsFromDb(id)
            val pics = fetchPicsFromDb(id)
            return UserProfile(bio = bio, pics = pics, friends = friends, id = id)
        }

        private fun fetchFriendsFromDb(
            id: String,
        ): List<String> {
            Thread.sleep(500)
            return emptyList()
        }

        private fun fetchPicsFromDb(
            id: String,
        ): List<String> {
            Thread.sleep(100)
            return emptyList()
        }

        private fun fetchBioHttp(id: String): String {
            Thread.sleep(1000)
            return "Bio"
        }
    }


}

private fun main() {
    val time = measureTimeMillis {
        val profile = Blocking.profile("id")


    }
    println(time)

}