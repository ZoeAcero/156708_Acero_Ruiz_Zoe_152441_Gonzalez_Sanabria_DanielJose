plugins {
    id("com.android.application") version "8.13.1" apply true
    id("org.jetbrains.kotlin.android") version "2.2.21" apply true
    id("org.jetbrains.kotlin.kapt") version "2.2.21" apply true
}

android {
    namespace = "com.example.sensoresapp"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.sensoresapp"
        minSdk = 26
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.10.2")
    kapt("androidx.room:room-compiler:2.8.3") // Example of using kapt
}
