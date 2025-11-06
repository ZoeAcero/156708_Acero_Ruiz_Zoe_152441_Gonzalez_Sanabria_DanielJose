// Versiones de las librerías a utilizar
val roomVersion = "2.6.1"
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
    // CRÍTICO: Actualizado a 36
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.sensoresapp"
        minSdk = 24
        // CRÍTICO: Actualizado a 36
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

dependencies {
    // Dependencias base
    // Si libs.core.ktx incluye androidx.core:1.17.0, el error de AGP es inevitable
    // sin actualizar el AGP. Asumimos que actualizar el SDK resuelve el conflicto de librerías.
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.core.ktx) // <= Esta dependencia exige el SDK 36

    // FIREBASE: Usar BOM
    implementation(platform("com.google.firebase:firebase-bom:$firebaseBomVersion"))

    // Firebase Auth
    implementation("com.google.firebase:firebase-auth-ktx")

    // Room
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
    implementation(libs.junit) // Se debe usar implementation() si está en libs.versions.toml
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}