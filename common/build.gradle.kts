plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

android {
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

    implementation(project(":common-ui"))
    implementation(project(":common-entities"))
    implementation(project(":common-utils"))

    implementation(libs.coroutines.android)
    implementation(libs.appcompat)
    implementation(libs.lifecycle.extensions)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.glide)
    annotationProcessor(libs.glide.compiler)
    implementation(libs.timber)
    implementation(libs.koin.core)
    implementation(libs.koin.android)
    implementation(libs.navigation.fragment.ktx)
    implementation(libs.navigation.ui.ktx)
}
