package ru.altf000.multimodule.common_network.network.adapter.result

import okio.IOException
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.altf000.multimodule.common_network.network.adapter.HttpException
import ru.altf000.multimodule.common_network.network.adapter.RequestResult

internal class ResultCall<T>(proxy: Call<T>) : CallDelegate<T, RequestResult<T>>(proxy) {

    override fun enqueueImpl(callback: Callback<RequestResult<T>>) {
        proxy.enqueue(ResultCallback(this, callback))
    }

    override fun cloneImpl(): ResultCall<T> {
        return ResultCall(proxy.clone())
    }

    private class ResultCallback<T>(
        private val proxy: ResultCall<T>,
        private val callback: Callback<RequestResult<T>>
    ) : Callback<T> {

        override fun onResponse(call: Call<T>, response: Response<T>) {
            val result: RequestResult<T>
            if (response.isSuccessful) {
                @Suppress("UNCHECKED_CAST")
                result = RequestResult.Success.HttpSuccess(
                    value = response.body() as T,
                    statusCode = response.code(),
                    statusMessage = response.message(),
                    url = call.request().url.toString(),
                )
            } else {
                result = RequestResult.Failure.HttpError(
                    HttpException(
                        statusCode = response.code(),
                        statusMessage = response.message(),
                        url = call.request().url.toString(),
                    )
                )
            }
            callback.onResponse(proxy, Response.success(result))
        }

        override fun onFailure(call: Call<T>, error: Throwable) {
            val result = when (error) {
                is retrofit2.HttpException -> RequestResult.Failure.HttpError(
                    HttpException(error.code(), error.message(), cause = error)
                )
                is IOException -> RequestResult.Failure.Error(error)
                else -> RequestResult.Failure.Error(error)
            }
            callback.onResponse(proxy, Response.success(result))
        }
    }

    override fun timeout(): Timeout {
        return proxy.timeout()
    }
}