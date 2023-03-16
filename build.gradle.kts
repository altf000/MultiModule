buildscript {

    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }

    dependencies {
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
