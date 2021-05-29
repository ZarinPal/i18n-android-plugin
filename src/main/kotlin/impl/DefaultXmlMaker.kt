package io.github.imanx.impl

import io.github.imanx.utils.Utils
import org.gradle.api.Project
import org.gradle.kotlin.dsl.withGroovyBuilder
import java.io.File
import java.io.StringWriter


class DefaultXmlMaker(private val project: Project) {


    fun make(name: String, map: Map<String, Any>) {

        map.forEach {
            val file = File("${project.rootDir}/app/src/main/res/values/strings_${name.toLowerCase()}.xml")
            val sw = StringWriter()

            groovy.xml.MarkupBuilder(sw).apply {
                doubleQuotes = true
                mkp.also { mrk ->
                    mrk.xmlDeclaration(mapOf("version" to "1.0", "encoding" to "utf-8"))
                    mrk.comment("Generated at ${Utils.now} ZarinPal Mobile Team\n Author: ImanX")
                    mrk.yield("\r\n")
                    this.withGroovyBuilder {
                        "resources" {
                            map.keys.forEach {
                                val key = it.takeIf { Utils.isJavaKeyword(it) }?.let { "${it}_" } ?: it
                                "string"("name" to "${name.toLowerCase()}_${key}") { mrk.yield(map[it]) }
                            }
                        }
                    }
                }
            }


            file.writeText(sw.toString())
            return

        }


    }

}