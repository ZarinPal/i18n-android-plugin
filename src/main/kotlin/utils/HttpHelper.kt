package io.github.imanx.utils

import java.net.HttpRetryException
import java.net.HttpURLConnection
import java.net.URL

object HttpHelper {
    fun request(url: String, protocol: String = "https://"): String {
        val a = "$protocol$url"
        println(a)
        val httpURLConnection = URL(a).openConnection() as HttpURLConnection
        httpURLConnection.run {
            requestMethod = "GET"
            setRequestProperty("Content-Type", "application/json; charset=UTF-8")
            doOutput = true
            connect()
            if (this.responseCode != 200) {
                throw HttpRetryException(responseMessage, responseCode)
            }

            return String(inputStream.readBytes())
        }
    }
}