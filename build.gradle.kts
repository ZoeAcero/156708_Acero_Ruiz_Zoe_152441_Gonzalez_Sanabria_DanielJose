plugins {
    id("com.android.application") version "8.13.1" apply false
    id("org.jetbrains.kotlin.android") version "2.2.21" apply false
    id("kotlin-kapt") version "1.9.0" apply false
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}