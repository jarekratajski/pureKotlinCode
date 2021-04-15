repositories {
    mavenLocal()
    jcenter()

}

plugins {
    kotlin("jvm") version "1.4.31"
    id("io.gitlab.arturbosch.detekt").version("1.16.0")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("io.vavr:vavr-kotlin:0.10.2") {
        exclude(group="org.jetbrains.kotlin")
    }
    //detektPlugins("pl.setblack:kure-potlin:0.5.0")
}


val compileKotlin: org.jetbrains.kotlin.gradle.tasks.KotlinCompile by tasks
compileKotlin.kotlinOptions.apply {
    jvmTarget = "1.8"
    javaParameters = true
    allWarningsAsErrors = false
    freeCompilerArgs = listOf("-XXLanguage:+InlineClasses")
}
