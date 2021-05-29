package io.github.imanx

import io.github.imanx.impl.DefaultDeserializer
import io.github.imanx.impl.DefaultXmlMaker
import org.gradle.api.DefaultTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.TaskAction


class I18nPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        val task = target.tasks.create("syncAll", SyncTask::class.java)
        task.group = "zarinpal i18n"
    }
}


open class SyncTask : DefaultTask() {

    @TaskAction
    fun run() {
//        val urlExtension = project.extensions.findByName("urls") as? UrlExtension ?: Localisation.arrayOfLocalisations
//        I18nContext(Localisation.arrayOfLocalisations)
//            .build()

        I18nContext(
            Localisation.arrayOfLocalisations,
            DefaultDeserializer(),
            DefaultXmlMaker(project)
        ).build()


    }
}


fun main() {
//    I18nContext(
//        Localisation.arrayOfLocalisations,
//        DefaultDeserializer(),
//        DefaultXmlMaker(p)
//    ).build()
}