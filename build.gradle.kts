plugins {
    id("com.android.application") version "8.8.0" apply false
    id("org.jetbrains.kotlin.android") version "2.0.0" apply false
}

buildscript {
    dependencies {
        // classpath("com.android.tools.build:gradle:8.1.1")
        // classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.0")
    }
}

subprojects {
    afterEvaluate {
        // Configure component metadata rules
        configurations.all {
            resolutionStrategy.eachDependency {
                if (requested.group == "com.android.tools.build" && requested.name == "gradle") {
                    useVersion("8.1.4")
                }
            }
        }
    }
}