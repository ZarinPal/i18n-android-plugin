import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
    `maven-publish`
}


group = "io.github.imanx"
version = "0.0.3"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.json:json:20171018")
    compileOnly(gradleApi())
}
tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

gradlePlugin {
    (plugins) {
        register("i18n") {
            id = "io.github.imanx.i18n"
            implementationClass = "io.github.imanx.I18nPlugin"
        }
    }
}
