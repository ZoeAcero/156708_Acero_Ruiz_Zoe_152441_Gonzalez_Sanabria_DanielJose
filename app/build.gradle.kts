// Versiones de las librerías a utilizar
val roomVersion = "2.6.1"
val lifecycleVersion = "2.6.2"
val coroutinesVersion = "1.7.3"
val firebaseBomVersion = "32.7.0" // Versión estable de Firebase BOM

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)

    // 1. Necesario para procesar las anotaciones de Room
    kotlin("kapt")

    // 2. Necesario para Firebase (Google Services)
    id("com.google.gms.google-services") version "4.4.0"
}

android {
    namespace = "com.example.a156708_acero_ruiz_zoe_152441_gonzalez_sanabria_danieljose"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.example.a156708_acero_ruiz_zoe_152441_gonzalez_sanabria_danieljose"
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
}

dependencies {
    // Dependencias existentes
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.core.ktx)

    // ---------------------------------------------------------------------
    // ➡️ DEPENDENCIAS AÑADIDAS PARA EL EJERCICIO
    // ---------------------------------------------------------------------

    // 1. FIREBASE: Usar BOM para gestionar las versiones
    implementation(platform("com.google.firebase:firebase-bom:$firebaseBomVersion"))

    // ➡️ ROOM (Persistencia de datos local)
    implementation("androidx.room:room-runtime:$roomVersion")
    implementation("androidx.room:room-ktx:$roomVersion")
    // Procesador de anotaciones de Room (MUY IMPORTANTE)
    kapt("androidx.room:room-compiler:$roomVersion")

    // ➡️ ARQUITECTURA (ViewModel y LiveData)
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion")

    // ➡️ CORRUTINAS (Para tareas asíncronas)
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion")
    // Necesario para la función .await() de Firebase Tasks
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.7.3")


    // ➡️ FIREBASE (Autenticación)
    // Se declara sin versión porque la BOM ya la define
    implementation("com.google.firebase:firebase-auth-ktx")

    // ---------------------------------------------------------------------

    // Dependencias de Test existentes
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}