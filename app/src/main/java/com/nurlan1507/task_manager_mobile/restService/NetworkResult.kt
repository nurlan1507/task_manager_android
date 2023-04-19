package com.nurlan1507.task_manager_mobile.restService

sealed class NetworkResult<T>(
    val message:String? = null,
    val code:Int,
    val data:T?=null
){
    class Success<T>(code: Int, data:T):NetworkResult<T>(code=code, data = data)
    class Failure<T>(code: Int, message: String?):NetworkResult<T>(code=code, message = message)

}