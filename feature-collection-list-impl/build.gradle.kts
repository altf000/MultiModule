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

    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(project(":module-injector"))
    implementation(project(":common-network"))
    implementation(project(":common-entities"))
    implementation(project(":common-ui"))
    implementation(project(":common"))
    implementation(project(":feature-collection-list-api"))

    val dagger = rootProject.extra["dagger_version"]
    compileOnly("javax.annotation:jsr250-api:1.0")
    implementation("com.google.dagger:dagger:$dagger")
    kapt("com.google.dagger:dagger-compiler:$dagger")

    val cicerone = rootProject.extra["cicerone_version"]
    implementation("ru.terrakok.cicerone:cicerone:$cicerone")

    val coroutines = rootProject.extra["coroutines_version"]
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines")

    val appCompat = rootProject.extra["appcompat_version"]
    implementation("androidx.appcompat:appcompat:$appCompat")

    val recyclerView = rootProject.extra["recyclerview_version"]
    implementation("androidx.recyclerview:recyclerview:$recyclerView")

    val constraint = rootProject.extra["constraintlayout_version"]
    implementation("androidx.constraintlayout:constraintlayout:$constraint")

    val lifecycleKtx = rootProject.extra["lifecycle_ktx_version"]
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleKtx")

    val lifecycleExtensions = rootProject.extra["lifecycle_extensions_version"]
    implementation("androidx.lifecycle:lifecycle-extensions:$lifecycleExtensions")

    val glide = rootProject.extra["glide_version"]
    implementation("com.github.bumptech.glide:glide:$glide")
    annotationProcessor("com.github.bumptech.glide:compiler:$glide")

    val swipeRefresh = rootProject.extra["swipe_refresh_version"]
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:$swipeRefresh")

    val pagingVersion = "3.0.0-rc01"
    implementation("androidx.paging:paging-runtime:$pagingVersion")

    val timber = rootProject.extra["timber_version"]
    implementation("com.jakewharton.timber:timber:$timber")
}
