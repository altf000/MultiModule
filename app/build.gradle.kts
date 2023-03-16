plugins {
    id("com.android.application")
    id("androidx.navigation.safeargs")
    kotlin("android")
    kotlin("kapt")
}

android {

    namespace = "ru.altf000.multimodule"
    compileSdk = rootProject.extra["compileSdkVersion"] as Int

    defaultConfig {
        applicationId = "ru.altf000.multimodule"
        minSdk = rootProject.extra["minSdkVersion"] as Int
        targetSdk = rootProject.extra["compileSdkVersion"] as Int
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        named("release") {
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

    kapt {
        correctErrorTypes = true
    }

    packaging {
        resources.excludes.add("META-INF/versions/9/previous-compilation-data.bin")
    }
}

dependencies {

    implementation(project(":common-network"))
    implementation(project(":common-db"))
    implementation(project(":common"))
    implementation(project(":common-ui"))
    implementation(project(":common-utils"))
    implementation(project(":common-entities"))
    implementation(project(":feature-collection-list"))
    implementation(project(":feature-collections"))
    implementation(project(":feature-content-detail"))
    implementation(project(":feature-recommendation-api"))
    implementation(project(":feature-recommendation-impl"))
    implementation(project(":common-utils"))

    implementation(libs.appcompat)
    implementation(libs.coroutines.android)
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    kapt(libs.room.compiler)
    implementation(libs.timber)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.fragment.ktx)
    implementation(libs.koin.core)
    implementation(libs.koin.android)
    implementation(libs.navigation.fragment.ktx)
    implementation(libs.navigation.ui.ktx)
    implementation(libs.material)
}
