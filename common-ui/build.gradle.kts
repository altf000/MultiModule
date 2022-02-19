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

    implementation(project(":common-utils"))
    implementation(project(":common-entities"))

    implementation(libs.recyclerview)
    implementation(libs.appcompat)
    implementation(libs.glide)
    annotationProcessor(libs.glide.compiler)
    implementation(libs.lifecycle.common)
    implementation(libs.paging)
    implementation(libs.coroutines.android)
    implementation(libs.constraintlayout)
    implementation(libs.material)
    implementation(libs.core.ktx)
}
