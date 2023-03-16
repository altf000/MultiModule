plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

android {

    namespace = "ru.altf000.multimodule.feature_recommendation_impl"
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

    implementation(project(":common-network"))
    implementation(project(":common-db"))
    implementation(project(":common-entities"))
    implementation(project(":common"))
    implementation(project(":feature-recommendation-api"))

    implementation(libs.coroutines.android)
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    kapt(libs.room.compiler)
    implementation(libs.timber)
    implementation(libs.koin.core)
    implementation(libs.koin.android)
}
