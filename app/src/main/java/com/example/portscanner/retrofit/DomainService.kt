package com.example.portscanner.retrofit

import com.example.portscanner.model.Address
import com.scanner.scanner.model.DomainCVE
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface DomainService {
    @POST("search/website")
    fun getDomainCVE(@Body address: Address): Call<DomainCVE>
}