package io.github.imanx

import io.github.imanx.impl.DefaultDeserializer
import io.github.imanx.impl.DefaultXmlMaker
import io.github.imanx.utils.HttpHelper


class I18nContext(
    private val urls: Array<Localisation>,
    private val deserializer: DefaultDeserializer,
    private val xmlMaker: DefaultXmlMaker
) {
    fun build() {
        urls.forEach { l ->
            HttpHelper.request(l.url)
                .let { deserializer.adapt(raw = it) }
                .let { xmlMaker.make(l.javaClass.simpleName, it.toMap()) }

        }
    }
}