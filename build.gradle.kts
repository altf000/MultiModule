allprojects {
    val minSdkVersion by extra(21)
    val compileSdkVersion by extra(31)
}

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.4.1")
    }
}

task<Delete>("clean") {
    delete(rootProject.buildDir)
}