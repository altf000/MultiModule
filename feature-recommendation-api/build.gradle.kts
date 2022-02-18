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

    implementation(project(":module-injector"))
    implementation(project(":common-network"))
    implementation(project(":common-entities"))

    val coroutines = rootProject.extra["coroutines_version"]
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines")
}
