buildscript {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
    dependencies {
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.4.1")
        classpath("com.github.ben-manes:gradle-versions-plugin:0.42.0")
    }
}

allprojects {

    val minSdkVersion by extra(21)
    val compileSdkVersion by extra(31)

    apply {
        plugin("com.github.ben-manes.versions")
    }
}

task<Delete>("clean") {
    delete(rootProject.buildDir)
}