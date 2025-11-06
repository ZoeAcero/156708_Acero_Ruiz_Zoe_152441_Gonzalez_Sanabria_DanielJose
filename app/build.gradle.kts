// Versiones de las librerías a utilizar
val roomVersion = "2.7.0-alpha01" // Versión actualizada para compatibilidad con Kotlin
val lifecycleVersion = "2.6.2"
val coroutinesVersion = "1.7.3"
val firebaseBomVersion = "32.7.0" // Versión estable de Firebase BOM

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.sensoresapp"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.sensoresapp"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    kapt {
        correctErrorTypes = true
    }
}

// Bloque añadido para solucionar el IllegalAccessError (Opción 1)
kotlin {
    // Esto configura el toolchain para usar Java 17, que es compatible con KAPT.
    // Si Java 17 no está instalado, Android Studio ofrecerá descargarlo.
    jvmToolchain(17)
}

dependencies {
    // Dependencias base
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.core.ktx)

    // FIREBASE: Usar BOM
    implementation(platform("com.google.firebase:firebase-bom:$firebaseBomVersion"))

    // Firebase Auth
    implementation("com.google.firebase:firebase-auth-ktx")

    // Room - Usando la versión actualizada para compatibilidad
    implementation("androidx.room:room-runtime:$roomVersion")
    implementation("androidx.room:room-ktx:$roomVersion")
    kapt("androidx.room:room-compiler:$roomVersion")

    // ViewModel y LiveData
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion")

    // Corrutinas
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:$coroutinesVersion")

    // Tests
    implementation(libs.junit)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}