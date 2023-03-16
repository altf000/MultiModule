plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

android {

    namespace = "ru.altf000.multimodule.common_network"
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

    implementation(project(":common-entities"))

    implementation(libs.appcompat)
    implementation(libs.retrofit)
    implementation(libs.gson)
    implementation(libs.gson.converter)
    implementation(libs.logginginterceptor)
    implementation(libs.coroutines.android)
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    kapt(libs.room.compiler)
    implementation(libs.timber)
    implementation(libs.koin.core)
    implementation(libs.coroutines.android)
}
