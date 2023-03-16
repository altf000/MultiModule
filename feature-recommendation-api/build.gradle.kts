plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

android {

    namespace = "ru.altf000.multimodule.feature_recommendation_api"
    compileSdk = rootProject.extra["compileSdkVersion"] as Int

    defaultConfig {
        minSdk = rootProject.extra["minSdkVersion"] as Int
        targetSdk = rootProject.extra["compileSdkVersion"] as Int
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"))
            proguardFiles("proguard-rules.pro")
        }
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(project(":common-network"))
    implementation(project(":common-entities"))

    implementation(libs.coroutines.android)
}
