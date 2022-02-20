package com.transit.information.control.repositories

import com.transit.information.control.managers.APIManager
import com.transit.information.model.Transit
import retrofit2.Call
import javax.inject.Inject

class MainRepository @Inject constructor() : MainRepo {

    // Method(GetAPI):
    override fun getAPI(): Call<Transit> = APIManager.createAPI().getAPI()
}