package com.heckbot

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.statement.HttpStatement
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class KtorNetwork: Network {
    private var _url: String = ""
    private val _parameters = mutableListOf<Pair<String, String>>()
    private val _headers = mutableListOf<Pair<String, String>>()

    override fun request(callback: NetworkCallback) {
        var response = ""
        CoroutineScope(Dispatchers.IO).launch {
            val status = HttpClient(OkHttp).use { client ->
                val httpStatement: HttpStatement = client.get(_url) {
                    _parameters.forEach { param ->
                        parameter(param.first, param.second)
                    }
                    _headers.forEach { header ->
                        headers.append(header.first, header.second)
                    }
                }

                response = httpStatement.receive<String>()
                callback(response)
            }
        }
    }

    override fun setUrl(url: String) {
        _url = url
    }

    override fun addParameters(vararg parameters: Pair<String, String>) {
        this._parameters.addAll(parameters)
    }

    override fun addHeaders(vararg headers: Pair<String, String>) {
        this._headers.addAll(headers)
    }
}