package uz.artikov.viewdataandlivedatareally.networking

import retrofit2.Call
import retrofit2.http.GET
import uz.artikov.viewdataandlivedatareally.models.UserData

interface ApiService {

    @GET("users")
    fun getUsers(): Call<List<UserData>>

}