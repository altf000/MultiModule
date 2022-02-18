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

    implementation(project(":feature-collection-list-api"))
    implementation(project(":feature-movie-detail-api"))
    implementation(project(":module-injector"))
    implementation(project(":common-ui"))
    implementation(project(":common-entities"))
    implementation(project(":constants"))

    val coroutines = rootProject.extra["coroutines_version"]
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines")

    val appCompat = rootProject.extra["appcompat_version"]
    implementation("androidx.appcompat:appcompat:$appCompat")

    val lifecycleExtensions = rootProject.extra["lifecycle_extensions_version"]
    implementation("androidx.lifecycle:lifecycle-extensions:$lifecycleExtensions")

    val dagger = rootProject.extra["dagger_version"]
    compileOnly("javax.annotation:jsr250-api:1.0")
    implementation("com.google.dagger:dagger:$dagger")
    kapt("com.google.dagger:dagger-compiler:$dagger")

    val cicerone = rootProject.extra["cicerone_version"]
    implementation("com.github.terrakok:cicerone:$cicerone")

    val glide = rootProject.extra["glide_version"]
    implementation("com.github.bumptech.glide:glide:$glide")
    annotationProcessor("com.github.bumptech.glide:compiler:$glide")
}
