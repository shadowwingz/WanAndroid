package com.shadowwingz.network

import com.shadowwingz.utils.Option
import com.shadowwingz.utils.SLog
import io.reactivex.functions.Consumer
import retrofit2.HttpException

/**
 * 封装
 */
abstract class ApiCallback<M> : Consumer<M> {
    abstract fun onSuccess(model: M)
    abstract fun onFailure(msg: String?)
    abstract fun onFinish()

    override fun accept(e: M) {
        if (e is HttpException) {
            val code = e.code()
            var msg = e.message()
            SLog.d("code = $code")
            if (code == 504) {
                msg = "网络不给力"
            }
            if (code == 502 || code == 404) {
                msg = "服务器异常，请稍后再试"
            }
            onFailure(msg)
        } else {
            if (Option.showLog()) {
                SLog.d(e.toString())
            }
            onSuccess(e)
        }
        onFinish()
    }
}