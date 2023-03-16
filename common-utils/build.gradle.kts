plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

android {

    namespace = "ru.altf000.multimodule.common_utils"
    compileSdk = rootProject.extra["compileSdkVersion"] as Int

    defaultConfig {
        minSdk = rootProject.extra["minSdkVersion"] as Int
        targetSdk = rootProject.extra["compileSdkVersion"] as Int
    }

    buildTypes {
        named("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"))
            proguardFiles("proguard-rules.pro")
        }
    }
}

dependencies {
    implementation(libs.coroutines.android)
    implementation(libs.lifecycle.extensions)
    implementation(libs.lifecycle.runtime.ktx)
}
