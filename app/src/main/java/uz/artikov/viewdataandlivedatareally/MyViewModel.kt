package uz.artikov.viewdataandlivedatareally

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Response
import uz.artikov.viewdataandlivedatareally.models.UserData
import uz.artikov.viewdataandlivedatareally.networking.ApiClient
import uz.artikov.viewdataandlivedatareally.networking.ApiService
import uz.artikov.viewdataandlivedatareally.utils.UserResource


//Landscape va portret o'zgargandan,ma'lumotni saqlab qoladi
class MyViewModel : ViewModel() {


    private var liveData = MutableLiveData<UserResource>(UserResource.Loading)


    init {

        fetchUsers()

    }

    private fun fetchUsers() {

        ApiClient.getRetrofit().create(ApiService::class.java).getUsers()
            .enqueue(object : retrofit2.Callback<List<UserData>> {
                override fun onResponse(
                    call: Call<List<UserData>>,
                    response: Response<List<UserData>>
                ) {

                    if (response.isSuccessful) {
                        liveData.postValue(UserResource.Success(response.body()))
                    } else {

                        when (response.code()) {

                            in 400..499 -> liveData.postValue(UserResource.Error("Client error"))

                            in 500..599 -> liveData.postValue(UserResource.Error("Server error"))

                        }

                    }

                }

                override fun onFailure(call: Call<List<UserData>>, t: Throwable) {

                    liveData.postValue(UserResource.Error(t.message ?: ""))

                }

            })

    }

    fun getUserList(): LiveData<UserResource> {

        return liveData

    }

}