package com.shadowwingz.wanandroid.architecture.response;

/**
 * created by shadowwingz on 2021-06-20 14:55
 */
class DataResult<T>(entity: T?, responseStatus: ResponseStatus) {
  private var mEntity: T?
  private var mResponseStatus: ResponseStatus

  init {
    mEntity = entity
    mResponseStatus = responseStatus
  }

  fun getResult(): T? {
    return mEntity
  }

  fun getResponseStatus(): ResponseStatus {
    return mResponseStatus
  }

  interface Result<T> {
    fun onResult(dataResult: DataResult<T>?)
  }
}