package io.github.imanx.impl

import org.json.JSONObject


class DefaultDeserializer {
    fun adapt(raw: String, parentKey: String? = null): Map<String, String> {
        val map = mutableMapOf<String, String>()

        JSONObject(raw).run {
            val keys = keys().iterator()

            while (keys.hasNext()) {

                val key = keys.next()

                if (get(key) is JSONObject) {
                    adapt(get(key).toString(), key).also { map.putAll(it) }
                    continue
                }


                val keyName = (parentKey?.let { "${parentKey}_$key" } ?: key).toLowerCase()

                map[keyName] = get(key).toString()
            }
        }

        return map
    }


}