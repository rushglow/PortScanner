package com.example.portscanner.retrofit

object Common {
    private val BASE_URL = "http://10.0.2.2:9090/"
    val domainService: DomainService
        get() = RetrofitClient.getClient(BASE_URL).create(DomainService::class.java)
}