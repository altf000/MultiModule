plugins {
    id("com.android.library")
    id("androidx.navigation.safeargs")
    kotlin("android")
    kotlin("kapt")
}

android {

    namespace = "ru.altf000.multimodule.feature_collections"
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
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(project(":common-network"))
    implementation(project(":common-entities"))
    implementation(project(":common-ui"))
    implementation(project(":common"))
    implementation(project(":common-utils"))

    implementation(libs.coroutines.android)
    implementation(libs.appcompat)
    implementation(libs.recyclerview)
    implementation(libs.constraintlayout)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.lifecycle.extensions)
    implementation(libs.glide)
    annotationProcessor(libs.glide.compiler)
    implementation(libs.swipetorefresh)
    implementation(libs.paging)
    implementation(libs.timber)
    implementation(libs.fragment.ktx)
    implementation(libs.koin.core)
    implementation(libs.koin.android)
    implementation(libs.navigation.fragment.ktx)
    implementation(libs.navigation.ui.ktx)
}
