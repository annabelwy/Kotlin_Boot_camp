plugins {
    kotlin("jvm") version "1.9.21"
//    kotlin("kapt") version "1.9.21"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    implementation("com.squareup.moshi:moshi-kotlin:1.11.0")
//    implementation("com.squareup.moshi:moshi-kotlin-reflect:1.11.0")
//    kapt("com.squareup.moshi:moshi-kotlin-codegen:1.11.0")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}