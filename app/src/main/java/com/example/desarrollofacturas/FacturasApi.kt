package com.example.desarrollofacturas
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FacturasApi {
    @GET("/facturas")
    suspend fun getFacturas() : Response<FacturaList>
}