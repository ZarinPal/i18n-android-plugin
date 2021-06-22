package io.github.imanx.impl

import org.json.JSONObject
import java.util.regex.Pattern


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

                map[keyName] = get(key)
                    .toString()
                    .toPlaceHolderFormat()
            }
        }

        return map
    }


    private fun String.toPlaceHolderFormat(): String {
        var newString = this
        Pattern.compile("\\{([^}]+)}")
            .run { matcher(this@toPlaceHolderFormat) }
            .apply {
                var index = 0
                while (find()) {
                    newString = newString.replace(group(), "{$index}")
                    index++
                }
            }

        return newString
    }

}