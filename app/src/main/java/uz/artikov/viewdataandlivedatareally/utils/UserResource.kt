package uz.artikov.viewdataandlivedatareally.utils

import uz.artikov.viewdataandlivedatareally.models.UserData


//Jarayonni kuzatib turish uchun
sealed interface UserResource {

    object Loading : UserResource

    data class Success(val list: List<UserData>?):UserResource

    data class Error(val message:String):UserResource


}