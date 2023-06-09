package com.nurlan1507.task_manager_mobile.restService

import android.util.Log
import org.json.JSONObject
import retrofit2.Response

abstract class BaseApiResponse {
    suspend fun <T> saveApiCall(api: suspend ()-> Response<T>):NetworkResult<T>{
        try {
            val response = api()
            val body =response.body()
            val errorBody = response.errorBody()?.string()
            if(response.isSuccessful){
                val headers = response.headers()
                body?.let {
                    return NetworkResult.Success(data=body, code=response.code())
                }?:return NetworkResult.Failure( message ="body is empty", code = response.code())
            } else{
                return NetworkResult.Failure( message = errorBody,  code = response.code())
            }
        }catch (e:Exception){
            e.printStackTrace()
            Log.d("apiError", e.message.toString())
            return NetworkResult.Failure( message ="Api call failed" + e.message.toString(), code = 500)
        }
    }
}
