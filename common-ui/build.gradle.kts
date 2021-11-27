plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdkVersion(rootProject.extra["compileSdkVersion"] as Int)

    defaultConfig {
        minSdkVersion(rootProject.extra["minSdkVersion"] as Int)
        targetSdkVersion(rootProject.extra["compileSdkVersion"] as Int)
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"))
            proguardFiles("proguard-rules.pro")
        }
    }
}

dependencies {

    implementation(project(":common-entities"))

    val recyclerView = rootProject.extra["recyclerview_version"]
    implementation("androidx.recyclerview:recyclerview:$recyclerView")

    val appCompat = rootProject.extra["appcompat_version"]
    implementation("androidx.appcompat:appcompat:$appCompat")

    val glide = rootProject.extra["glide_version"]
    implementation("com.github.bumptech.glide:glide:$glide")
    annotationProcessor("com.github.bumptech.glide:compiler:$glide")
}
