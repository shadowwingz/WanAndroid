package com.shadowwingz.wanandroid.home.data.article

// object DataRepository {
//
//   private val retrofit: Retrofit
//
//   init {
//     val logging = HttpLoggingInterceptor()
//     logging.level = HttpLoggingInterceptor.Level.BODY
//     val client = OkHttpClient.Builder()
//       .connectTimeout(8, TimeUnit.SECONDS)
//       .readTimeout(8, TimeUnit.SECONDS)
//       .writeTimeout(8, TimeUnit.SECONDS)
//       .addInterceptor(logging)
//       .build()
//     retrofit = Retrofit.Builder()
//       .baseUrl(APIs.BASE_URL)
//       .client(client)
//       .addConverterFactory(GsonConverterFactory.create())
//       .build()
//   }
//
//   suspend fun requestLogin(userBean: UserBean): LoginResponse {
//     return retrofit.create(AccountService::class.java).login(userBean.name, userBean.password)
//   }
//
// }