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
}

dependencies {

    implementation(project(":module-injector"))
    implementation(project(":common-entities"))

    val appCompat = rootProject.extra["appcompat_version"]
    implementation("androidx.appcompat:appcompat:$appCompat")

    val dagger = rootProject.extra["dagger_version"]
    compileOnly("javax.annotation:jsr250-api:1.0")
    implementation("com.google.dagger:dagger:$dagger")
    kapt("com.google.dagger:dagger-compiler:$dagger")

    val retrofit = rootProject.extra["retrofit_version"]
    implementation("com.squareup.retrofit2:retrofit:$retrofit")

    val gson = rootProject.extra["gson_version"]
    implementation("com.google.code.gson:gson:$gson")

    val converter = rootProject.extra["gson_converter_version"]
    implementation("com.squareup.retrofit2:converter-gson:$converter")

    val interceptor = rootProject.extra["logging_interceptor_version"]
    implementation("com.squareup.okhttp3:logging-interceptor:$interceptor")

    val coroutines = rootProject.extra["coroutines_version"]
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines")

    val room = rootProject.extra["room_version"]
    implementation("androidx.room:room-runtime:$room")
    kapt("androidx.room:room-compiler:$room")
    implementation("androidx.room:room-ktx:$room")

    val timber = rootProject.extra["timber_version"]
    implementation("com.jakewharton.timber:timber:$timber")
}
