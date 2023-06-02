plugins {
    kotlin("jvm") version "1.8.21"
}

kotlin {
    jvmToolchain(8)

    sourceSets {
        val test by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}
