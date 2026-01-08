plugins {
    kotlin("jvm") version "1.9.22"
    application
}

group = "com.boggle"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    // Kotlin Standard Library
    implementation(kotlin("stdlib"))
    
    // Coroutines for async operations
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    
    // Testing
    testImplementation(kotlin("test"))
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.1")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}

application {
    mainClass.set("com.boggle.MainKt")
}
