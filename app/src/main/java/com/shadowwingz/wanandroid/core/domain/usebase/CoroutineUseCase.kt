package com.shadowwingz.wanandroid.core.domain.usebase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

abstract class CoroutineUseCase<P, R>(private val coroutineDispatcher: CoroutineDispatcher) {

  suspend operator fun invoke(parameters: P): R {
    return withContext(coroutineDispatcher) {
      execute(parameters)
    }
  }

  @Throws(RuntimeException::class)
  protected abstract suspend fun execute(parameters: P): R
}