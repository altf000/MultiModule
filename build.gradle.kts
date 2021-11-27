allprojects {
    val minSdkVersion by extra(21)
    val compileSdkVersion by extra(31)
    val kotlin_version by extra("1.6.0")
    val dagger_version by extra("2.40")
    val cicerone_version by extra("7.1")
    val glide_version by extra("4.12.0")
    val coroutines_version by extra("1.6.0-RC")
    val appcompat_version by extra("1.2.0")
    val recyclerview_version by extra("1.2.1")
    val constraintlayout_version by extra("2.1.2")
    val lifecycle_ktx_version by extra("2.4.0")
    val lifecycle_extensions_version by extra("2.2.0")
    val retrofit_version by extra("2.9.0")
    val gson_version by extra("2.8.9")
    val gson_converter_version by extra("2.7.2")
    val logging_interceptor_version by extra("4.9.0")
    val room_version by extra("2.3.0")
    val timber_version by extra("5.0.1")
    val swipe_refresh_version by extra("1.1.0")
    val fragment_ktx_version by extra("1.4.0")
}

task<Delete>("clean") {
    delete(rootProject.buildDir)
}