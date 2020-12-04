package net.simplifiedcoding.loginapp.data.network

import net.simplifiedcoding.loginapp.data.network.responses.AuthResponse
import net.simplifiedcoding.loginapp.data.network.responses.QuotesResponse
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface MyApi {

  //  @Headers({"Content-Type:application/json","IMSI:357175048449937","IMEI:510110406068589"})
    @FormUrlEncoded
    @POST("login")
    suspend fun userLogin(
        @Field("username") email: String,
        @Field("pssword") password: String
    ) : Response<AuthResponse>


    companion object{
        operator fun invoke(
            networkConnectionInterceptor: NetworkConnectionInterceptor
        ) : MyApi{

            val okkHttpclient = OkHttpClient.Builder()
                .addInterceptor(networkConnectionInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okkHttpclient)
                .baseUrl("http://private-222d3-homework5.apiary-mock.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MyApi::class.java)
        }
    }

}

