plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
}

apply {
    plugin("kotlin-android")
}

android {

    compileSdkVersion(rootProject.extra["compileSdkVersion"] as Int)

    defaultConfig {
        applicationId = "ru.altf000.multimodule"
        minSdkVersion(rootProject.extra["minSdkVersion"] as Int)
        targetSdkVersion(rootProject.extra["compileSdkVersion"] as Int)
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"))
            proguardFiles("proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(project(":module-injector"))
    implementation(project(":common-network"))
    implementation(project(":common-db"))
    implementation(project(":common"))
    implementation(project(":constants"))
    implementation(project(":common-entities"))
    implementation(project(":feature-collection-list-api"))
    implementation(project(":feature-collection-list-impl"))
    implementation(project(":feature-movie-detail-api"))
    implementation(project(":feature-movie-detail-impl"))
    implementation(project(":feature-recommendation-api"))
    implementation(project(":feature-recommendation-impl"))

    val appCompat = rootProject.extra["appcompat_version"]
    implementation("androidx.appcompat:appcompat:$appCompat")

    val cicerone = rootProject.extra["cicerone_version"]
    implementation("ru.terrakok.cicerone:cicerone:$cicerone")

    val dagger = rootProject.extra["dagger_version"]
    compileOnly("javax.annotation:jsr250-api:1.0")
    implementation("com.google.dagger:dagger:$dagger")
    kapt("com.google.dagger:dagger-compiler:$dagger")

    val coroutines = rootProject.extra["coroutines_version"]
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines")

    val room = rootProject.extra["room_version"]
    implementation("androidx.room:room-runtime:$room")
    kapt("androidx.room:room-compiler:$room")
    implementation("androidx.room:room-ktx:$room")

    val timber = rootProject.extra["timber_version"]
    implementation("com.jakewharton.timber:timber:$timber")

    val lifecycleKtx = rootProject.extra["lifecycle_ktx_version"]
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleKtx")

    val fragmentKtx = rootProject.extra["fragment_ktx_version"]
    implementation("androidx.fragment:fragment-ktx:$fragmentKtx")
}
