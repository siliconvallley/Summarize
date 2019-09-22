package com.dh.http.uiils

import com.dh.http.BaseDataF
import com.google.gson.Gson
import com.google.gson.JsonParser
import org.json.JSONObject

/**
 * @author : 86351
 * @date : 2018/12/20
 * @description : Gson解析
 * @version :
 */
object GsonUtils {

    fun toObject(json: String): BaseDataF {
        val baseDataF = BaseDataF()
        val jsonObject = JSONObject(json)
        baseDataF.code = jsonObject.optInt("code")
        baseDataF.message = jsonObject.optString("message")
        // 将data中的json数据都当做字符串来出来
        baseDataF.data = jsonObject.optString("data")
        return baseDataF
    }

    fun <T> fromJson(json: String, clazz: Class<T>): T {
        return Gson().fromJson(json, clazz)
    }

    fun toJson(any: Any): String {
        return Gson().toJson(any)
    }

    fun <T> fromJsonList(json: String, clazz: Class<T>): MutableList<T> {
        val list: MutableList<T> = mutableListOf()
        val array = JsonParser().parse(json).asJsonArray
        for (element in array) {
            list.add(Gson().fromJson(element, clazz))
        }
        return list
    }
}