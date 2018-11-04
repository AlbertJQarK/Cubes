package com.albertjsoft.cubes.data.api.observer

import android.content.Context
import android.support.annotation.CallSuper
import android.widget.Toast
import com.albertjsoft.cubes.app.CubesApp
import com.albertjsoft.cubes.data.api.response.HttpStatus
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import retrofit2.HttpException
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.net.SocketTimeoutException


abstract class ApiObserver<T> : Observer<T> {

    override fun onSubscribe(d: Disposable) {}

    override fun onNext(response: T) { onSuccess(response) }

    abstract fun onSuccess(response: T)

    override fun onComplete(){}

    @CallSuper
    override fun onError(exception: Throwable) {
        when (exception) {
            is HttpException -> onHttpException(exception)
            is SocketTimeoutException -> onTimeout()
            is IOException -> onNetworkError()
            else -> onUnknownError(exception.message)
        }
    }

    private fun onHttpException(httpException: HttpException) {
        val statusCode = httpException.response().code()

        when (statusCode) {
            HttpStatus.NOT_FOUND -> onNotFoundError(httpException)
            HttpStatus.BAD_REQUEST -> onBadRequestError(httpException)
            HttpStatus.INTERNAL_SERVER_ERROR -> onInternalServerError(httpException)
            else -> { onUnknownError(getErrorMessage(httpException)) }
        }
    }

    private fun onNetworkError() {
       CubesApp.instance.showToast("Ups!", "Network error")

    }

    private fun onTimeout() {
        CubesApp.instance.showToast("Ups!", "Timeout")
    }

    private fun onUnknownError(errorMessage: String?) {
        CubesApp.instance.showToast("Unknown Error", errorMessage)
    }

    private fun onNotFoundError(httpException: HttpException) {
        CubesApp.instance.showToast("Not Found", getErrorMessage(httpException))
    }

    private fun onBadRequestError(httpException: HttpException) {
        CubesApp.instance.showToast("Bad Request", getErrorMessage(httpException))
    }

    private fun onInternalServerError(httpException: HttpException) {
        CubesApp.instance.showToast("Internal server error",getErrorMessage(httpException))
    }

    private fun getErrorMessage(httpException: HttpException): String? {
        return try {
            JSONObject(httpException.response().errorBody()!!.string()).getString("err")
        } catch (exception: JSONException) {
            exception.message
        } catch (exception: IOException) {
            exception.message
        }
    }

    private fun Context?.showToast(title: String?, message: String?) {
        Toast.makeText(this, title.plus( " - ").plus(message), Toast.LENGTH_SHORT).show()
    }
}
