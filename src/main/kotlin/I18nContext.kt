package io.github.imanx

import io.github.imanx.impl.DefaultDeserializer
import io.github.imanx.impl.DefaultXmlMaker
import io.github.imanx.utils.HttpHelper
import org.json.JSONObject


class I18nContext(
    private val region: Region = Region.fa_IR,
    private val deserializer: DefaultDeserializer,
    private val xmlMaker: DefaultXmlMaker
) {
    fun build() {
        getEntities().forEach { item -> writeJsonToXml(item.first, item.second) }
    }

    private fun getEntities(): List<Pair<String, String>> {
        return HttpHelper.request(Const.rootRawUrl)
            .run { JSONObject(this) }
            .let {
                val baseUrl = it.getString("end_point")
                it.getJSONArray("entities")
                    .map { it.toString().replace(".json", "") to "$baseUrl/${region.value}/$it" }
                    .toList()

            }
    }

    private fun writeJsonToXml(name: String, url: String) {
        HttpHelper.request(url)
            .let { deserializer.adapt(raw = it) }
            .let { xmlMaker.make(name, it.toMap()) }
    }


}


