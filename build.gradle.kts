buildscript {

    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:8.1.0-alpha09")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.5.3")
        classpath("com.github.ben-manes:gradle-versions-plugin:0.42.0")
    }
}

allprojects {

    val minSdkVersion by extra(24)
    val compileSdkVersion by extra(33)

    apply {
        plugin("com.github.ben-manes.versions")
    }
}

task<Delete>("clean") {
    delete(rootProject.buildDir)
}