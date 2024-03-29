package ru.altf000.multimodule.common_network.network.adapter.result

import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import ru.altf000.multimodule.common_network.network.adapter.RequestResult
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class ResultAdapterFactory : CallAdapter.Factory() {

    override fun get(
        returnType: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit,
    ): CallAdapter<*, *>? {
        val rawReturnType: Class<*> = getRawType(returnType)
        if (rawReturnType == Call::class.java) {
            if (returnType is ParameterizedType) {
                val callInnerType: Type = getParameterUpperBound(0, returnType)
                if (getRawType(callInnerType) == RequestResult::class.java) {
                    // resultType is Call<Result<*>> | callInnerType is Result<*>
                    if (callInnerType is ParameterizedType) {
                        val resultInnerType = getParameterUpperBound(0, callInnerType)
                        return ResultCallAdapter<Any?>(resultInnerType)
                    }
                    return ResultCallAdapter<Nothing>(Nothing::class.java)
                }
            }
        }
        return null
    }
}

private class ResultCallAdapter<R>(private val type: Type) :
    CallAdapter<R, Call<RequestResult<R>>> {
    override fun responseType() = type
    override fun adapt(call: Call<R>): Call<RequestResult<R>> = ResultCall(call)
}