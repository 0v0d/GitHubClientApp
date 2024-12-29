package com.example.feature.utils

import com.google.gson.Gson

object JsonUtils {
    private val gson = Gson()

    fun <T> fromJson(json: String, clazz: Class<T>): T {
        return gson.fromJson(json, clazz)
    }

    fun toJson(obj: Any): String {
        return gson.toJson(obj)
    }
}