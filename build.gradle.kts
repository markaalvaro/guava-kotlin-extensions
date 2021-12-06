import nl.javadude.gradle.plugins.license.LicenseExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.0"
    id("com.github.hierynomus.license") version "0.16.1"
}

group = "com.github.markaalvaro"
version = "0.0"

repositories {
    mavenCentral()
}

dependencies {
    api("com.google.guava:guava:31.0.1-jre")
    testImplementation("org.jetbrains.kotlin:kotlin-test:1.6.0")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

configure<LicenseExtension> {
    header = project.file("header.txt")
    exclude("**/Piece.kt") // Uses UTF-8 for now for pretty chess pieces
}