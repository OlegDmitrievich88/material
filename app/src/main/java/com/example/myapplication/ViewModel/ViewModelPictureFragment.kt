package com.example.myapplication.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.myapplication.BuildConfig
import com.example.myapplication.Model.PODRetrofitImpl
import com.example.myapplication.Model.PODServerResponseData
import com.example.myapplication.Model.PictureOfTheDayApi
import com.example.myapplication.Model.PictureOfTheDayData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModelPictureFragment(
    private val liveDataForViewToObserver: MutableLiveData<PictureOfTheDayData> = MutableLiveData(),
    private val retrofitImpl: PODRetrofitImpl = PODRetrofitImpl()
): ViewModel() {

     fun getData(): LiveData<PictureOfTheDayData>{
         sendServerRequest()
        return  liveDataForViewToObserver
    }

    private fun sendServerRequest(){
        liveDataForViewToObserver.value = PictureOfTheDayData.Loading(null)

        val apiKey: String = BuildConfig.NASA_API_KEY

        if (apiKey.isBlank()){
            PictureOfTheDayData.Error(Throwable("API KEY NNNAADA"))
        }else{
            retrofitImpl.getRetrofitImpl().getPictureOTheDay(apiKey).enqueue(object :
                Callback<PODServerResponseData>{
                override fun onResponse(
                    call: Call<PODServerResponseData>,
                    response: Response<PODServerResponseData>
                ) {
                     if (response.isSuccessful && response.body() != null){
                        liveDataForViewToObserver.value = PictureOfTheDayData.Success(response.body()!!)
                    }else{
                        val  message = response.message()
                        if (message.isNullOrEmpty()){
                            liveDataForViewToObserver.value = PictureOfTheDayData.Error(Throwable("undefield error"))
                        }else{
                            liveDataForViewToObserver.value = PictureOfTheDayData.Error(Throwable(message))
                        }
                    }
                }

                override fun onFailure(call: Call<PODServerResponseData>, t: Throwable) {
                    liveDataForViewToObserver.value = PictureOfTheDayData.Error(t)
                }
            })
        }

    }

}