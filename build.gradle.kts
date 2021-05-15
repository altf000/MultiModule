buildscript {

    val kotlin_version by extra("1.5.0")
    val dagger_version by extra("2.34")
    val cicerone_version by extra("5.1.1")
    val glide_version by extra("4.12.0")
    val coroutines_version by extra("1.5.0")
    val appcompat_version by extra("1.2.0")
    val recyclerview_version by extra("1.1.0")
    val constraintlayout_version by extra("2.0.4")
    val lifecycle_ktx_version by extra("2.4.0-alpha01")
    val lifecycle_extensions_version by extra("2.2.0")
    val retrofit_version by extra("2.9.0")
    val gson_version by extra("2.8.6")
    val gson_converter_version by extra("2.7.2")
    val logging_interceptor_version by extra("4.9.0")
    val room_version by extra("2.2.6")
    val timber_version by extra("4.7.1")
    val swipe_refresh_version by extra("1.1.0")
    val fragment_ktx_version by extra("1.3.6")

    repositories {
        google()
        jcenter()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:4.1.3")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")


        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
    val minSdkVersion by extra(21)
    val compileSdkVersion by extra(30)
}

task<Delete>("clean") {
    delete(rootProject.buildDir)
}