plugins {
    kotlin("jvm") version "1.8.21"
    id("org.jetbrains.kotlinx.binary-compatibility-validator") version "0.13.1"
}

kotlin {
    jvmToolchain(8)
    explicitApi()

    sourceSets {
        val main by getting {
            dependencies {
                implementation("org.ow2.asm:asm:9.5")
            }
        }
        val test by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}
