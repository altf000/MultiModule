package ru.altf000.multimodule.common_network.network.adapter.flow

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Response
import ru.altf000.multimodule.common_network.network.adapter.HttpException
import ru.altf000.multimodule.common_network.network.adapter.RequestResult
import java.io.IOException
import java.lang.reflect.Type
import java.util.concurrent.atomic.AtomicBoolean

internal class FlowCallAdapter<R>(private val responseType: Type) :
    CallAdapter<R, Flow<RequestResult<R>>> {

    override fun responseType() = responseType

    @ExperimentalCoroutinesApi
    override fun adapt(call: Call<R>): Flow<RequestResult<R>> {
        return callbackFlow {
            val started = AtomicBoolean(false)
            if (started.compareAndSet(false, true)) {
                call.enqueue(object : Callback<R> {

                    override fun onResponse(call: Call<R>, response: Response<R>) {
                        val result: RequestResult<R>
                        if (response.isSuccessful) {
                            result = RequestResult.Success.HttpResponse(
                                value = response.body() as R,
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
                        offer(result)
                        channel.close()
                    }

                    override fun onFailure(call: Call<R>, throwable: Throwable) {
                        val result = when (throwable) {
                            is retrofit2.HttpException -> RequestResult.Failure.HttpError(
                                HttpException(
                                    throwable.code(),
                                    throwable.message(),
                                    cause = throwable
                                )
                            )
                            is IOException -> RequestResult.Failure.Error(throwable)
                            else -> RequestResult.Failure.Error(throwable)
                        }
                        offer(result)
                        channel.close()
                    }
                })
            }
            awaitClose { call.cancel() }
        }
    }
}